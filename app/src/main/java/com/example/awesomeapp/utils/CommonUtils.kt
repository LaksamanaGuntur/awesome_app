package com.example.awesomeapp.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.awesomeapp.R

object CommonUtils {

    private val TAG = CommonUtils::class.java.simpleName

    fun getSwipeRefreshColor(context: Context): Int = ContextCompat.getColor(context, R.color.teal_200)
}