package com.archetecture.androidmvvm.data.network

import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Nain Kazi on 4/24/2019.
 */

interface APIServiceKt {

    @get:GET("bins/nl6jh/")
    val newsFeeds: Call<JsonObject>

}
