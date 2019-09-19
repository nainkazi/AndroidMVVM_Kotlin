package com.archetecture.androidmvvm.utils

import com.archetecture.androidmvvm.data.models.NewsEntityKt
import com.google.gson.*
import java.lang.reflect.Type

class NewsResponseDeserializerKt : JsonDeserializer<NewsEntityKt>{
    val KEY_MULTIMEDIA = "multimedia"
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): NewsEntityKt {
        if (json!!.getAsJsonObject().get(KEY_MULTIMEDIA) !is JsonArray) {
            json.getAsJsonObject().add(KEY_MULTIMEDIA, JsonArray())
        }
        return Gson().fromJson(json, NewsEntityKt::class.java)
    }

}