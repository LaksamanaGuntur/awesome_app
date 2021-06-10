package com.example.awesomeapp.ui.activity.detail

import androidx.databinding.ObservableField
import com.example.awesomeapp.data.DataManager
import com.example.awesomeapp.ui.base.BaseViewModel

class DetailViewModel(dataManager: DataManager) : BaseViewModel<DetailInterface>(dataManager) {

    val title: ObservableField<String> = ObservableField()

    fun setTitle(title: String) {
        this.title.set(title)
    }

    companion object {
        private val TAG = DetailViewModel::class.java.simpleName
    }
}