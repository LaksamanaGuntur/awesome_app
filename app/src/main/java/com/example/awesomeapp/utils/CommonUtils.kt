package com.example.awesomeapp.utils

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.awesomeapp.R

object CommonUtils {

    private val TAG = CommonUtils::class.java.simpleName

    fun showLoadingDialog(context: Context): ProgressDialog = ProgressDialog.show(context, "", "Loading")

//    fun showLoadingDialog(context: Context?): ProgressDialog {
//        val progressDialog = ProgressDialog(context)
//        progressDialog.let {
//            it.show()
//            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
//            it.setContentView(R.layout.progress_dialog)
//            it.isIndeterminate = true
//            it.setCancelable(false)
//            it.setCanceledOnTouchOutside(false)
//            return it
//        }
//    }

    fun showToast(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun getSwipeRefreshColor(context: Context): Int = ContextCompat.getColor(context, R.color.teal_200)
}