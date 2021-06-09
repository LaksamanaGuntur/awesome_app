package com.example.awesomeapp.ui.activity.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.awesomeapp.BR
import com.example.awesomeapp.R
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.databinding.ActivityHomeBinding
import com.example.awesomeapp.ui.ViewModelProviderFactory
import com.example.awesomeapp.ui.activity.home.adapter.ItemHomeAdapter
import com.example.awesomeapp.ui.base.BaseActivity
import com.example.awesomeapp.utils.CommonUtils
import javax.inject.Inject


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeInterface, ItemHomeAdapter.Listener {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var layoutManager: GridLayoutManager
    @Inject
    lateinit var adapter: ItemHomeAdapter

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getViewModel(): HomeViewModel {
        viewModel = ViewModelProviders.of(this, factory)[HomeViewModel::class.java]
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        viewModel.setNavigator(this)
        adapter.setListener(this, this)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        initializeData()
    }

    private fun initializeData() {
        initializeAdapter()
        initializeSwipeRefresh()
        subscribeLiveData()
    }

    private fun initializeAdapter() {
        binding.listItem.layoutManager = layoutManager
        binding.listItem.adapter = adapter
    }

    private fun initializeSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.swipeRefresh.setColorSchemeColors(CommonUtils.getSwipeRefreshColor(this))
    }

    private fun subscribeLiveData() {
        viewModel.photoLiveData.observe(this, {
            viewModel.photoObservableList.clear()
            viewModel.photoObservableList.addAll(it)
        })
    }

    private fun animateRecyclerLayoutChange(layoutSpanCount: Int) {
        if (layoutManager.spanCount != layoutSpanCount) {
            val fadeOut: Animation = AlphaAnimation(1f, 0f)
            fadeOut.interpolator = DecelerateInterpolator()
            fadeOut.duration = 400
            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    layoutManager.spanCount = layoutSpanCount
                    layoutManager.requestLayout()
                    val fadeIn: Animation = AlphaAnimation(0f, 1f)
                    fadeIn.interpolator = AccelerateInterpolator()
                    fadeIn.duration = 400
                    binding.listItem.startAnimation(fadeIn)
                }
            })
            binding.listItem.startAnimation(fadeOut)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_grid -> {
                animateRecyclerLayoutChange(2)
                return true
            }
            R.id.action_list -> {
                animateRecyclerLayoutChange(1)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun itemOnClick(photo: Photo) {

    }
}