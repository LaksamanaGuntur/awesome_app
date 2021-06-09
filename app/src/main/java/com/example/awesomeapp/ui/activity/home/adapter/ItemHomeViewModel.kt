package com.example.awesomeapp.ui.activity.home.adapter

import androidx.databinding.ObservableField
import com.example.awesomeapp.data.model.Photo

class ItemHomeViewModel(private val photo: Photo, private val listener: Listener) {

    val name: ObservableField<String> = ObservableField(photo.photographer)
    val profile: ObservableField<String> = ObservableField(photo.photographerUrl)
    val image: ObservableField<String> = ObservableField(photo.src?.small)

    fun itemOnClick() = listener.itemOnClick(photo)

    interface Listener {
        fun itemOnClick(photo: Photo)
    }
}