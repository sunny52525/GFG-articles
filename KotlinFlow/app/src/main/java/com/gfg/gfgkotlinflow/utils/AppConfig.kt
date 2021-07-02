package com.gfg.gfgkotlinflow.utils

import com.gfg.gfgkotlinflow.network.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConfig {

    //Base url of the api
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    //create retrofit service
    fun ApiService(): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
}