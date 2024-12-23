package com.example.hackathon_2k24.domain.network

import android.os.Build
import com.example.hackathon_2k24.BuildConfig

object ServiceMethods {

    fun baseUrl() = BuildConfig.APP_URL

    val DEVICE_NAME = Build.MANUFACTURER + Build.MODEL


    const val PRODUCTS = "products"

}