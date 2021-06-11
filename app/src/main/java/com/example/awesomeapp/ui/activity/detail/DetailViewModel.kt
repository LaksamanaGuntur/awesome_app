package com.example.awesomeapp.ui.activity.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.awesomeapp.data.DataManager
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.ui.base.BaseViewModel
import com.example.awesomeapp.utils.rx.SchedulerProvider

class DetailViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<DetailInterface>(dataManager, schedulerProvider) {

    val title: ObservableField<String> = ObservableField()
    val imageUrl: ObservableField<String> = ObservableField()
    val liked: ObservableBoolean = ObservableBoolean()

    fun setData(photo: Photo) {
        title.set(photo.photographer)
        imageUrl.set(photo.src?.large)
        liked.set(photo.liked)
    }

    companion object {
        private val TAG = DetailViewModel::class.java.simpleName
    }
}