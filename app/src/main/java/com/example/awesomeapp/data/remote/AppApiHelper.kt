package com.example.awesomeapp.data.remote

import com.example.awesomeapp.data.model.Response
import com.example.awesomeapp.utils.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val schedulerProvider: SchedulerProvider, private val apiHelper: ApiHelper): ApiHelper {

    override fun getCurated(page: String, perPage: String): Single<Response> {
        return apiHelper.getCurated(page, perPage)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

    override fun getPhotos(id: String): Single<Response> {
        return apiHelper.getPhotos(id)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}