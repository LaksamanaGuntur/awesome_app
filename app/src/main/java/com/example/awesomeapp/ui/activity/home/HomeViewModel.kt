package com.example.awesomeapp.ui.activity.home

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.example.awesomeapp.data.DataManager
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.ui.base.BaseViewModel
import com.google.gson.Gson

class HomeViewModel(dataManager: DataManager) : BaseViewModel<HomeInterface>(dataManager) {

    var photoLiveData: MutableLiveData<List<Photo>> = MutableLiveData()
    var photoObservableList: ObservableList<Photo> = ObservableArrayList()

    init {
        loadData()
    }

    fun loadData() {
        setIsLoading(true)
        getCompositeDisposable().add(getDataManager()
            .getCurated("1", "20")
            .subscribe({
                setIsLoading(false)
                photoLiveData.postValue(it.photos)
                Log.d(TAG, "response : " + Gson().toJson(it))
            }, {
                handleApiError(TAG, it)
            })
        )
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}