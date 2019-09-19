package com.archetecture.androidmvvm.data.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * This class represents a media item
 */
class MediaEntityKt : Parcelable {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("format")
    @Expose
    var format: String? = null
    @SerializedName("height")
    @Expose
    var height: Int = 0
    @SerializedName("width")
    @Expose
    var width: Int = 0
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("subtype")
    @Expose
    var subType: String? = null
    @SerializedName("capton")
    @Expose
    var caption: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    constructor(`in`: Parcel) {
        url = `in`.readString()
        format = `in`.readString()
        height = `in`.readInt()
        width = `in`.readInt()
        type = `in`.readString()
        subType = `in`.readString()
        caption = `in`.readString()
        copyright = `in`.readString()
    }

    constructor() {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(url)
        parcel.writeString(format)
        parcel.writeInt(height)
        parcel.writeInt(width)
        parcel.writeString(type)
        parcel.writeString(subType)
        parcel.writeString(caption)
        parcel.writeString(copyright)
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<MediaEntityKt> = object : Parcelable.Creator<MediaEntityKt> {
            override fun createFromParcel(`in`: Parcel): MediaEntityKt {
                return MediaEntityKt(`in`)
            }

            override fun newArray(size: Int): Array<MediaEntityKt?> {
                return arrayOfNulls(size)
            }
        }
    }
}
