package com.example.awesomeapp.ui.activity.detail

import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProviders
import com.example.awesomeapp.BR
import com.example.awesomeapp.R
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.databinding.ActivityDetailBinding
import com.example.awesomeapp.ui.ViewModelProviderFactory
import com.example.awesomeapp.ui.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(), DetailInterface {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var photo: Photo? = null

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun getViewModel(): DetailViewModel {
        viewModel = ViewModelProviders.of(this, factory)[DetailViewModel::class.java]
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        viewModel.setNavigator(this)

        initializeData()
    }

    private fun initializeData() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        photo = intent.extras?.getParcelable("bundleDetail")
        photo?.let {
            viewModel.setData(it)
            it.photographerUrl?.let { it1 -> binding.webview.loadUrl(it1) }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}