package com.gunwook.tourguide.presentation.contracts

import com.gunwook.tourguide.data.model.NearbyModel
import com.gunwook.tourguide.presentation.model.LocationModel

interface HomeMapperConstract {
    fun mapToModel(type : NearbyModel) : List<LocationModel>
}