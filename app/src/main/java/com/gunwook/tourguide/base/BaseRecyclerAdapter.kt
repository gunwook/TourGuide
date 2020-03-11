package com.gunwook.tourguide.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.gunwook.tourguide.presentation.BaseAdapterNavigator
import com.gunwook.tourguide.presentation.contracts.HomeContract
import com.gunwook.tourguide.presentation.presenter.BasePresenter

abstract class BaseRecyclerAdapter(val context: Context, private val presenter: BaseAdapterNavigator) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int {
        return presenter.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BaseViewHolder<*>)?.onBindViewHolder(presenter.getItem(position))
    }
}