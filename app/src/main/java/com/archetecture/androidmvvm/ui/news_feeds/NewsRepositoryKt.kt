package com.archetecture.androidmvvm.ui.news_feeds

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.archetecture.androidmvvm.data.models.NewsEntityKt
import com.archetecture.androidmvvm.data.network.APIClientKt
import com.archetecture.androidmvvm.data.network.APIServiceKt
import com.archetecture.androidmvvm.utils.NewsResponseDeserializerKt
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepositoryKt() {

    var listLiveData: MutableLiveData<List<NewsEntityKt>> = MutableLiveData<List<NewsEntityKt>>()
    var apiService: APIServiceKt? = null
    val KEY_RESULT = "results";

    fun getNewsFeed() {
        var apiClientKt = APIClientKt()
        apiService = apiClientKt.apiService
        var call: Call<JsonObject> = apiService!!.newsFeeds

        var impl = object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                listLiveData.value = null
            }

            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                val listType = object : TypeToken<List<NewsEntityKt>>() {}.type
                var gson: Gson = GsonBuilder().registerTypeAdapter(NewsEntityKt::class.java, NewsResponseDeserializerKt()).create()
                val jsonArray: JsonArray = response!!.body().getAsJsonArray(KEY_RESULT)
                var newsEntities: List<NewsEntityKt> = gson.fromJson(jsonArray.toString(), listType)
                listLiveData.value = newsEntities
            }
        }
        call.enqueue(impl)

    }


}