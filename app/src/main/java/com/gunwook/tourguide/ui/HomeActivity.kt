package com.gunwook.tourguide.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gunwook.tourguide.R
import com.gunwook.tourguide.base.BaseActivity
import com.gunwook.tourguide.presentation.BaseContract
import com.gunwook.tourguide.presentation.contracts.HomeContract
import com.gunwook.tourguide.ui.adapter.HomeAdapter
import com.gunwook.tourguide.ui.listener.PaginationScrollListener
import com.gunwook.tourguide.utils.Logd
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_main.*
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.jar.Manifest

class HomeActivity : BaseActivity(R.layout.activity_main) , HomeContract.View  {

    private val presenter : HomeContract.Presenter by inject { parametersOf(this) }
    private var mapView : MapView? = null

    override fun presenter(): BaseContract {
        return presenter
    }

    override fun initView() {

        val layoutManager = LinearLayoutManager(this)
        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.setHasFixedSize(true)
        homeRecyclerView.adapter = HomeAdapter(this , presenter)
        homeRecyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {

            override fun loadMoreItems() {
                presenter.getNeayBySearch()
            }

            override fun isLoading(): Boolean {
                return presenter.getLoading()
            }

            override fun isLastPage(): Boolean {
                return presenter.getLastPage()
            }
        })


        requestPemission()
    }

    fun requestPemission(){
        runWithPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION){
            presenter.getNeayBySearch()
        }
    }

    override fun locationChange(lat : Double , lon : Double) {
        if (mapView == null){

            mapView =  MapView(this)
            val mapPoint = MapPoint.mapPointWithGeoCoord(lat, lon)

            mapView?.setMapCenterPoint(mapPoint, true)
            mapView?.setZoomLevel(5 , true)
            frameView.addView(mapView)
        }
    }

    override fun notifyDataChange() {
        homeRecyclerView.adapter?.notifyDataSetChanged()
    }
}
