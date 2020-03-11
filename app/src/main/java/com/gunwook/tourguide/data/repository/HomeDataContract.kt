package com.gunwook.tourguide.data.repository

import com.gunwook.tourguide.data.model.NearbyModel

interface _HomeDataContract {
    /**
     * 근처에 있는 여행지를 가져옵니다.
     * */
    suspend fun getNearyBySearch(lat : String , lon : String , page : Int) : NearbyModel
    /**
     * 현재 위치정보를 가져옵니다.
     * */
    suspend fun getLocationSearch() : Pair<Double , Double>
}

class HomeDataContract : _HomeDataContract {

    override suspend fun getNearyBySearch(lat : String , lon : String , page : Int) : NearbyModel{
        throw UnsupportedOperationException()
    }

    override suspend fun getLocationSearch(): Pair<Double, Double> {
        throw UnsupportedOperationException()
    }
}