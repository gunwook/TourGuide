package com.gunwook.tourguide.ui.viewholder

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.gunwook.tourguide.base.BaseViewHolder
import com.gunwook.tourguide.presentation.contracts.HomeContract
import com.gunwook.tourguide.presentation.model.LocationModel
import com.gunwook.tourguide.presentation.presenter.HomePresenter
import kotlinx.android.synthetic.main.cell_list_place.view.*

class HomeViewHolder (context  : Context, @LayoutRes layoutRef : Int, parent : ViewGroup?, presenter : HomeContract.Presenter) : BaseViewHolder<LocationModel>(context, layoutRef,parent){

    private var nearIv : ImageView? = null
    private var titleTv : TextView? = null
    private var addrTv : TextView? = null
    init {
        nearIv = itemView.nearByIv
        titleTv = itemView.titleTv
        addrTv = itemView.addrTv
    }

    override fun onViewCreated(item: LocationModel?) {

        nearIv?.clipToOutline = true

        if (nearIv != null && item?.firstImage != null) Glide.with(context).load(item.firstImage).into(nearIv!!)

        titleTv?.text = item?.title
        addrTv?.text = item?.addr1
    }
}