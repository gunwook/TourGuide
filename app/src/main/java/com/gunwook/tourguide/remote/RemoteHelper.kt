package com.gunwook.tourguide.remote

import android.content.Context
import com.gunwook.tourguide.R
import com.gunwook.tourguide.data.model.NearbyModel
import org.json.JSONObject
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteHelper (private val remoteApi: RemoteApi) : KoinComponent {

    val context : Context by inject()

    suspend fun locationSearch(lat: String, lon : String , page : Int): NearbyModel {
        return remoteApi.locationSearch( context.getString(R.string.tour_api) , lon , lat , page)
    }
}