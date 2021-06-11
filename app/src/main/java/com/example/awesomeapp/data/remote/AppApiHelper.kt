package com.example.awesomeapp.data.remote

import com.example.awesomeapp.data.model.Response
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiHelper: ApiHelper): ApiHelper {

    override fun getCurated(page: Int, perPage: Int): Single<Response> {
        return apiHelper.getCurated(page, perPage)
    }
}