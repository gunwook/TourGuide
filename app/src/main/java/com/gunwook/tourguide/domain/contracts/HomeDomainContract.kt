package com.gunwook.tourguide.domain.contracts

import com.gunwook.tourguide.data.model.NearbyModel
import com.gunwook.tourguide.utils.LocationUtils

interface HomeDomainContract {

    /**
     * 근처에 있는 여행지를 가져옵니다.
     * */
    suspend fun getNearBySearch(lat : String,  lon : String , page : Int) : NearbyModel

    /**
     * 현재 위치정보를 가져옵니다.
     * */
    suspend fun getLocationSearch() : Pair<Double , Double>
}