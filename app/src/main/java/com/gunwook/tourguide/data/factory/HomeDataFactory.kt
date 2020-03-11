package com.gunwook.tourguide.data.factory

import com.gunwook.tourguide.data.repository.HomeDataContract
import com.gunwook.tourguide.data.repository._HomeDataContract
import com.gunwook.tourguide.data.source.HomeRemoteDataSource
import com.gunwook.tourguide.data.source.LocationDataSource
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class HomeDataFactory : KoinComponent{
    private val homeDataContract by lazy { HomeDataContract() }

    private val remoteDataSource : HomeRemoteDataSource by inject { parametersOf(homeDataContract)}
    private val locationDataSource : LocationDataSource by inject { parametersOf(homeDataContract)}

    fun retrieveRemoteDataSource() : _HomeDataContract {
        return remoteDataSource
    }

    fun retrieveLocationDataSource() : _HomeDataContract {
        return locationDataSource
    }

}