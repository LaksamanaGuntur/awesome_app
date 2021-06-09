package com.example.awesomeapp.data

import com.example.awesomeapp.data.model.Response
import com.example.awesomeapp.data.remote.ApiHelper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AppDataManager @Inject constructor(private var apiHelper: ApiHelper): DataManager {
    override fun getCurated(page: String, perPage: String): Single<Response> = apiHelper.getCurated(page, perPage)

    override fun getPhotos(id: String): Single<Response> = apiHelper.getPhotos(id)
}