package com.gunwook.tourguide.data.source

import android.content.Context
import com.gunwook.tourguide.data.model.NearbyModel
import com.gunwook.tourguide.data.repository._HomeDataContract
import com.gunwook.tourguide.utils.LocationUtils
import org.koin.core.inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationDataSource(repository : _HomeDataContract) : BaseDataSource() , _HomeDataContract by repository{
    override suspend fun getLocationSearch(): Pair<Double, Double> {
        val res = suspendCoroutine<Pair<Double, Double>> {
            LocationUtils(context , object : LocationUtils.LocationCallback {
                override fun onSuccess(isSuccess: Boolean, view: LocationUtils) {
                    if (isSuccess) it.resume(Pair(view.lat ?: 0.0 , view.lon ?: 0.0))
                    else it.resume(Pair(view.lat ?: 0.0 , view.lon ?: 0.0))
                }
            })
        }

        return Pair(res.first , res.second)
    }
}