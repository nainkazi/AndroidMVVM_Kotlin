package com.archetecture.androidmvvm.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nain Kazi on 4/24/2019.
 */

class APIClientKt {

    private val BASE_URL = "https://api.myjson.com/"

    val retroInstance: Retrofit
        get() {
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

        }

    val apiService: APIServiceKt
        get() = retroInstance.create(APIServiceKt::class.java)
}
