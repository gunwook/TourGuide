package com.gunwook.tourguide.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gunwook.tourguide.R
import com.gunwook.tourguide.base.BaseRecyclerAdapter
import com.gunwook.tourguide.presentation.contracts.HomeContract
import com.gunwook.tourguide.presentation.presenter.HomePresenter
import com.gunwook.tourguide.ui.viewholder.HomeViewHolder

class HomeAdapter(private val mContext : Context, private val presenter : HomeContract.Presenter) : BaseRecyclerAdapter(mContext , presenter ){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(mContext, R.layout.cell_list_place , parent , presenter)
    }
}