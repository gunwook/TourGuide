package com.gunwook.tourguide.presentation

interface BaseAdapterNavigator  {
    fun getItemViewType(position : Int) : Int
    fun getItem(position : Int) : Any?
    fun getItemCount() : Int
}