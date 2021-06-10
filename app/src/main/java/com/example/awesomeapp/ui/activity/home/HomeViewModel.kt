package com.example.awesomeapp.ui.activity.home

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.example.awesomeapp.data.DataManager
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.ui.base.BaseViewModel

class HomeViewModel(dataManager: DataManager) : BaseViewModel<HomeInterface>(dataManager) {

    var photos: MutableList<Photo> = mutableListOf()
    var photoLiveData: MutableLiveData<List<Photo>> = MutableLiveData()
    var photoObservableList: ObservableList<Photo> = ObservableArrayList()
    val imageUrl: ObservableField<String> = ObservableField()

    init {
        imageUrl.set("https://carbontracker.org/wp-content/uploads/2018/09/pexels-photo-157037.jpeg")
    }

    fun loadData(page: Int) {
        setIsLoading(true)
        getCompositeDisposable().add(getDataManager()
            .getCurated(page, 7)
            .subscribe({
                it.totalResults?.let { it1 ->
                    getNavigator()?.setTotalResult(it1) }

                it.photos?.let { it2 ->
                    photos.addAll(it2)
                    photoLiveData.postValue(photos)
                }
                setIsLoading(false)
            }, {
                handleApiError(TAG, it)
            })
        )
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}