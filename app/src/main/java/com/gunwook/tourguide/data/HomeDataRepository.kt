package com.gunwook.tourguide.data

import com.gunwook.tourguide.data.factory.HomeDataFactory
import com.gunwook.tourguide.data.model.NearbyModel
import com.gunwook.tourguide.domain.repository.HomeRepository
import org.koin.core.inject

class HomeDataRepository : HomeRepository , BaseDataRepository(){

    private val homeDataFactory : HomeDataFactory by inject()


    override suspend fun getNearbySearch(lat : String , lon : String, page : Int) : NearbyModel {
        return homeDataFactory.retrieveRemoteDataSource().getNearyBySearch(lat , lon , page)
    }

    override suspend fun getLocationSearch(): Pair<Double, Double> {
        return homeDataFactory.retrieveLocationDataSource().getLocationSearch()
    }
}