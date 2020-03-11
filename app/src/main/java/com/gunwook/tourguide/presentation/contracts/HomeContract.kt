package com.gunwook.tourguide.presentation.contracts

import com.gunwook.tourguide.presentation.BaseAdapterNavigator
import com.gunwook.tourguide.presentation.BaseContract

interface HomeContract {


    interface View {
        fun locationChange(lat : Double , lon : Double)
        fun notifyDataChange()
    }


    interface Presenter : BaseContract , BaseAdapterNavigator {
        fun getNeayBySearch()
        fun getLoading() : Boolean
        fun getLastPage() : Boolean

    }
}