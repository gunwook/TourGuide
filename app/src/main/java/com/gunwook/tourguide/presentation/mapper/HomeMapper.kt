package com.gunwook.tourguide.presentation.mapper

import com.gunwook.tourguide.data.model.NearbyModel
import com.gunwook.tourguide.presentation.contracts.HomeMapperConstract
import com.gunwook.tourguide.presentation.model.LocationModel

class HomeMapper : HomeMapperConstract {

    override fun mapToModel(type : NearbyModel) : List<LocationModel> {
        val listOfItems = mutableListOf<LocationModel>()

        for (model in type.body.items.items) {
            listOfItems.add(LocationModel(
                model.firstimage,
                model.contentid,
                model.createdtime,
                model.addr1,
                model.mapx,
                model.mapy,
                model.title
            ))
        }

        return listOfItems
    }

}