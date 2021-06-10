package com.example.awesomeapp.ui.base

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.awesomeapp.data.DataManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(private val dataManager: DataManager) : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val mIsLoading = ObservableBoolean()
    private lateinit var navigator: WeakReference<N>

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getCompositeDisposable(): CompositeDisposable = compositeDisposable

    fun getDataManager(): DataManager = dataManager

    fun getIsLoading(): ObservableBoolean = mIsLoading

    fun setIsLoading(isLoading: Boolean) = mIsLoading.set(isLoading)

    fun getNavigator(): N? = navigator.get()

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun handleApiError(TAG: String, throwable: Throwable) {
        throwable.printStackTrace()
        throwable.message?.let {
            Log.e(TAG, it)
        }
        setIsLoading(false)
    }
}