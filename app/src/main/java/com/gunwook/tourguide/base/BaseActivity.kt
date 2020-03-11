package com.gunwook.tourguide.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gunwook.tourguide.presentation.BaseContract

abstract class BaseActivity(layoutId : Int) : AppCompatActivity(layoutId)  {

    abstract fun initView()

    abstract fun presenter() : BaseContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter().onCreate()

        initView()
    }


    override fun onDestroy() {
        super.onDestroy()

        presenter().onDestroy()
    }
}

