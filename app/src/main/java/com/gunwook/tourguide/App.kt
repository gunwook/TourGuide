package com.gunwook.tourguide

import android.app.Application
import com.gunwook.tourguide.di.AppModules
import com.gunwook.tourguide.remote.RemoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(
                AppModules.presenters ,
                AppModules.dataModules ,
                AppModules.domains,
                AppModules.data
            ))
        }
    }
}