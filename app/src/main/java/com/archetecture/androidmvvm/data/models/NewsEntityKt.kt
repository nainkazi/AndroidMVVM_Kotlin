package com.archetecture.androidmvvm.data.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

import java.util.ArrayList

/**
 * This represents a news item
 */

@SuppressLint("ParcelCreator")
class NewsEntityKt : Parcelable {
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("abstract")
    @Expose
    var summary: String? = null
    @SerializedName("url")
    @Expose
    var articleUrl: String? = null
    @SerializedName("byline")
    @Expose
    var byline: String? = null
    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null
    @SerializedName("multimedia")
    @Expose
    var mediaEntityList: ArrayList<MediaEntityKt>? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        summary = parcel.readString()
        articleUrl = parcel.readString()
        byline = parcel.readString()
        publishedDate = parcel.readString()
    }


    constructor() {

    }



    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(summary)
        parcel.writeString(articleUrl)
        parcel.writeString(byline)
        parcel.writeString(publishedDate)
    }

    companion object CREATOR : Parcelable.Creator<NewsEntityKt> {
        override fun createFromParcel(parcel: Parcel): NewsEntityKt {
            return NewsEntityKt(parcel)
        }

        override fun newArray(size: Int): Array<NewsEntityKt?> {
            return arrayOfNulls(size)
        }
    }


}
