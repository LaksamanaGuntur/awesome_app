package com.example.awesomeapp.ui.base

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.awesomeapp.R
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.ui.activity.detail.DetailActivity
import com.example.awesomeapp.utils.CommonUtils
import com.example.awesomeapp.utils.NetworkUtils
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
        checkInternetConnection()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = getViewModel()
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

    open fun getViewDataBinding(): T = viewDataBinding

    open fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    open fun hideLoading() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    open fun showLoading() {
        hideLoading()
        progressDialog = CommonUtils.showLoadingDialog(this)
    }

    open fun showToast(message: String) {
        CommonUtils.showToast(this, message)
    }

    open fun isNetworkConnected() = NetworkUtils.isNetworkConnected(applicationContext)

    private fun performDependencyInjection() = AndroidInjection.inject(this)

    private fun checkInternetConnection() {
        if (!isNetworkConnected())
            showSnackBar(findViewById(android.R.id.content))
    }

    open fun showSnackBar(view: View) {
        val snackbar = Snackbar.make(view, this.getString(R.string.no_internet_label), Snackbar.LENGTH_SHORT)
        snackbar.setAction(R.string.dismiss_label) {
            snackbar.dismiss()
        }
        snackbar.setActionTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        snackbar.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.show()
    }

    fun goToDetailActivity(photo: Photo) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("bundleDetail", photo)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }
}