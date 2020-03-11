package com.gunwook.tourguide.presentation.presenter

import android.content.Context
import com.gunwook.tourguide.domain.contracts.HomeDomainContract
import com.gunwook.tourguide.presentation.contracts.HomeContract
import com.gunwook.tourguide.presentation.contracts.HomeMapperConstract
import com.gunwook.tourguide.presentation.model.LocationModel
import com.gunwook.tourguide.utils.LocationUtils
import com.gunwook.tourguide.utils.Logd
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomePresenter(val callback : HomeContract.View) : BasePresenter() , HomeContract.Presenter , KoinComponent{

    private val usecase : HomeDomainContract by inject()
    private val mapper : HomeMapperConstract by inject()
    private val listOfItems : MutableList<LocationModel> = mutableListOf()


    override fun onCreate() {
    }

    override fun onDestroy() {
        clear()
    }

    override fun getNeayBySearch() {
        coroutine.launch(handler) {
            if (isLoading) return@launch
            isLoading = true

            val res = async(Dispatchers.Main + job) {
                val location = usecase.getLocationSearch()

                callback.locationChange( location.first, location.second)

                location
            }.await()

            val model = usecase.getNearBySearch(res.first.toString(), res.second.toString(), mPage)

            if (model.body.pageNo * model.body.numOfNums >= model.body.totalCount) {
                isLast = true
            }

            if (mPage == 1) listOfItems.clear()

            listOfItems.addAll(mapper.mapToModel(model))

            withContext(Dispatchers.Main + job) {
                mPage += 1
                isLoading = false

                callback.notifyDataChange()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItem(position: Int): Any? {
        return listOfItems[position]
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun getLoading(): Boolean {
        return isLoading
    }

    override fun getLastPage(): Boolean {
        return isLast
    }
}