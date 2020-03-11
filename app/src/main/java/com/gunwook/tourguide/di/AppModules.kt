package com.gunwook.tourguide.di

import com.gunwook.tourguide.cache.PreferenceHelper
import com.gunwook.tourguide.data.BaseDataRepository
import com.gunwook.tourguide.data.HomeDataRepository
import com.gunwook.tourguide.data.factory.HomeDataFactory
import com.gunwook.tourguide.data.repository.HomeDataContract
import com.gunwook.tourguide.data.source.HomeRemoteDataSource
import com.gunwook.tourguide.data.source.LocationDataSource
import com.gunwook.tourguide.domain.contracts.HomeDomainContract
import com.gunwook.tourguide.domain.repository.HomeRepository
import com.gunwook.tourguide.domain.usecase.HomeUseCase
import com.gunwook.tourguide.presentation.contracts.HomeContract
import com.gunwook.tourguide.presentation.contracts.HomeMapperConstract
import com.gunwook.tourguide.presentation.mapper.HomeMapper
import com.gunwook.tourguide.presentation.presenter.HomePresenter
import com.gunwook.tourguide.remote.RemoteModule
import org.koin.dsl.module

object AppModules{
    val dataModules = module {
        single { RemoteModule() }
        single { PreferenceHelper() }
    }

    val presenters = module {
        factory<HomeContract.Presenter>{ (view: HomeContract.View) -> HomePresenter(view) }
        factory<HomeMapperConstract> { HomeMapper() }
    }

    val domains = module {
        factory <HomeDomainContract> { HomeUseCase() }
    }

    val data = module {
        factory <HomeRepository> { HomeDataRepository() }
        factory { (view: HomeDataContract) -> HomeRemoteDataSource(view) }
        factory { (view: HomeDataContract) -> LocationDataSource(view) }
        factory { HomeDataFactory() }
    }

}