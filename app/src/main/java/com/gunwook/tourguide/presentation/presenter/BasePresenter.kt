package com.gunwook.tourguide.presentation.presenter

import com.gunwook.tourguide.presentation.BaseContract
import com.gunwook.tourguide.utils.Logd
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.KoinComponent

abstract class BasePresenter : KoinComponent{

    protected val job = Job()
    protected val coroutine : CoroutineScope = CoroutineScope(Dispatchers.Default + job)
    protected var mPage : Int = 1
    protected var isLoading : Boolean = false
    protected var isLast : Boolean = false

    protected fun clear() {
        job.cancel()

        mPage = 1
        isLoading = false
        isLast = false
    }

    val handler = CoroutineExceptionHandler { coroutineContext, exception ->
        Logd.e("Caught $exception")
    }
}