package com.example.awesomeapp.ui.activity.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.awesomeapp.data.DataManager
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.ui.base.BaseViewModel

class DetailViewModel(dataManager: DataManager) : BaseViewModel<DetailInterface>(dataManager) {

    val title: ObservableField<String> = ObservableField()
    val imageUrl: ObservableField<String> = ObservableField()
    val liked: ObservableBoolean = ObservableBoolean()

    companion object {
        private val TAG = DetailViewModel::class.java.simpleName
    }

    fun setData(photo: Photo) {
        title.set(photo.photographer)
        imageUrl.set(photo.src?.large)
        liked.set(photo.liked)
    }
}