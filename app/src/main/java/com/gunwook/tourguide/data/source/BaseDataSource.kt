package com.gunwook.tourguide.data.source

import android.content.Context
import com.gunwook.tourguide.remote.RemoteHelper
import com.gunwook.tourguide.remote.RemoteModule
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseDataSource : KoinComponent {

    protected val remoteApi : RemoteModule by inject()
    protected val context : Context by inject()
}