package com.example.awesomeapp.data.remote

import com.example.awesomeapp.data.model.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ApiHelper {
    @GET("v1/curated")
    fun getCurated(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<Response>

    @GET("v1/photos/{id}")
    fun getPhotos(
        @Path("id") id: Int
    ): Single<Response>
}