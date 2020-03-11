package com.gunwook.tourguide.domain.repository

import com.gunwook.tourguide.data.model.NearbyModel

interface HomeRepository {

    /**
     * 근처에 있는 여행지를 가져옵니다.
     * */
    suspend fun getNearbySearch(lat : String,  lon : String , page : Int) : NearbyModel


    /**
     * 현재 위치정보를 가져옵니다.
     * */
    suspend fun getLocationSearch() : Pair<Double , Double>
}