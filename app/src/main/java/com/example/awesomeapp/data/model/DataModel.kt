package com.example.awesomeapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {
        @SerializedName("page")
        @Expose
        var page: Int? = null

        @SerializedName("per_page")
        @Expose
        var perPage: Int? = null

        @SerializedName("photos")
        @Expose
        var photos: List<Photo>? = null

        @SerializedName("total_results")
        @Expose
        var totalResults: Int? = null

        @SerializedName("next_page")
        @Expose
        var nextPage: String? = null

        @SerializedName("prev_page")
        @Expose
        var prevPage: String? = null
}

class Photo {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("width")
        @Expose
        var width: Int? = null

        @SerializedName("height")
        @Expose
        var height: Int? = null

        @SerializedName("url")
        @Expose
        var url: String? = null

        @SerializedName("photographer")
        @Expose
        var photographer: String? = null

        @SerializedName("photographer_url")
        @Expose
        var photographerUrl: String? = null

        @SerializedName("photographer_id")
        @Expose
        var photographerId: Int? = null

        @SerializedName("avg_color")
        @Expose
        var avgColor: String? = null

        @SerializedName("src")
        @Expose
        var src: Src? = null

        @SerializedName("liked")
        @Expose
        var liked: Boolean? = null
}

class Src {
        @SerializedName("original")
        @Expose
        var original: String? = null

        @SerializedName("large2x")
        @Expose
        var large2x: String? = null

        @SerializedName("large")
        @Expose
        var large: String? = null

        @SerializedName("medium")
        @Expose
        var medium: String? = null

        @SerializedName("small")
        @Expose
        var small: String? = null

        @SerializedName("portrait")
        @Expose
        var portrait: String? = null

        @SerializedName("landscape")
        @Expose
        var landscape: String? = null

        @SerializedName("tiny")
        @Expose
        var tiny: String? = null
}