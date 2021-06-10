package com.example.awesomeapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response (
        @SerializedName("page")
        @Expose
        var page: Int? = null,

        @SerializedName("per_page")
        @Expose
        var perPage: Int? = null,

        @SerializedName("photos")
        @Expose
        var photos: List<Photo>? = null,

        @SerializedName("total_results")
        @Expose
        var totalResults: Int? = null,

        @SerializedName("next_page")
        @Expose
        var nextPage: String? = null,

        @SerializedName("prev_page")
        @Expose
        var prevPage: String? = null,
)

class Photo (
        @SerializedName("id")
        @Expose
        var id: Int? = null,

        @SerializedName("width")
        @Expose
        var width: Int? = null,

        @SerializedName("height")
        @Expose
        var height: Int? = null,

        @SerializedName("url")
        @Expose
        var url: String? = null,

        @SerializedName("photographer")
        @Expose
        var photographer: String? = null,

        @SerializedName("photographer_url")
        @Expose
        var photographerUrl: String? = null,

        @SerializedName("photographer_id")
        @Expose
        var photographerId: Int? = null,

        @SerializedName("avg_color")
        @Expose
        var avgColor: String? = null,

        @SerializedName("src")
        @Expose
        var src: Src? = null,

        @SerializedName("liked")
        @Expose
        var liked: Boolean
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readParcelable(Src::class.java.classLoader),
                parcel.readByte() != 0.toByte()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeValue(id)
                parcel.writeValue(width)
                parcel.writeValue(height)
                parcel.writeString(url)
                parcel.writeString(photographer)
                parcel.writeString(photographerUrl)
                parcel.writeValue(photographerId)
                parcel.writeString(avgColor)
                parcel.writeParcelable(src, flags)
                parcel.writeByte(if (liked) 1 else 0)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Photo> {
                override fun createFromParcel(parcel: Parcel): Photo {
                        return Photo(parcel)
                }

                override fun newArray(size: Int): Array<Photo?> {
                        return arrayOfNulls(size)
                }
        }
}

class Src (
        @SerializedName("original")
        @Expose
        var original: String? = null,

        @SerializedName("large2x")
        @Expose
        var large2x: String? = null,

        @SerializedName("large")
        @Expose
        var large: String? = null,

        @SerializedName("medium")
        @Expose
        var medium: String? = null,

        @SerializedName("small")
        @Expose
        var small: String? = null,

        @SerializedName("portrait")
        @Expose
        var portrait: String? = null,

        @SerializedName("landscape")
        @Expose
        var landscape: String? = null,

        @SerializedName("tiny")
        @Expose
        var tiny: String? = null
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(original)
                parcel.writeString(large2x)
                parcel.writeString(large)
                parcel.writeString(medium)
                parcel.writeString(small)
                parcel.writeString(portrait)
                parcel.writeString(landscape)
                parcel.writeString(tiny)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Src> {
                override fun createFromParcel(parcel: Parcel): Src {
                        return Src(parcel)
                }

                override fun newArray(size: Int): Array<Src?> {
                        return arrayOfNulls(size)
                }
        }
}