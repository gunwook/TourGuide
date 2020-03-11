package com.gunwook.tourguide.data.source

import android.content.Context
import com.gunwook.tourguide.data.model.NearbyModel
import com.gunwook.tourguide.data.repository._HomeDataContract
import com.gunwook.tourguide.utils.LocationUtils
import org.koin.core.inject

class HomeRemoteDataSource(repository : _HomeDataContract) : BaseDataSource() , _HomeDataContract by repository{
    override suspend fun getNearyBySearch(lat : String , lon : String , page : Int) : NearbyModel {
        return remoteApi.getHelper().locationSearch(lat, lon , page )
    }
}