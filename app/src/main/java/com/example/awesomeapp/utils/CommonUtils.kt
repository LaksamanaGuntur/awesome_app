package com.example.awesomeapp.utils

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.awesomeapp.R

object CommonUtils {

    private val TAG = CommonUtils::class.java.simpleName

    fun showLoadingDialog(context: Context): ProgressDialog = ProgressDialog.show(context, "", "Loading")

    fun showToast(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun getSwipeRefreshColor(context: Context): Int = ContextCompat.getColor(context, R.color.teal_200)
}