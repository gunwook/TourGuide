package com.gunwook.tourguide.domain.usecase

import com.gunwook.tourguide.data.model.NearbyModel
import com.gunwook.tourguide.domain.BaseUseCase
import com.gunwook.tourguide.domain.contracts.HomeDomainContract
import com.gunwook.tourguide.domain.repository.HomeRepository
import org.koin.core.inject

class HomeUseCase : BaseUseCase() , HomeDomainContract  {

    private val homeRepository : HomeRepository by inject()

    override suspend fun getNearBySearch(lat : String,  lon : String , page : Int) : NearbyModel {
        return homeRepository.getNearbySearch(lat , lon , page)
    }

    override suspend fun getLocationSearch(): Pair<Double, Double> {
        return homeRepository.getLocationSearch()
    }
}