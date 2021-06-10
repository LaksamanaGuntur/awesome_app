package com.example.awesomeapp.ui.activity.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeapp.BR
import com.example.awesomeapp.R
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.databinding.ActivityHomeBinding
import com.example.awesomeapp.ui.ViewModelProviderFactory
import com.example.awesomeapp.ui.activity.home.adapter.ItemHomeAdapter
import com.example.awesomeapp.ui.activity.home.adapter.ItemHomeAdapter.Companion.SPAN_COUNT_LIST
import com.example.awesomeapp.ui.activity.home.adapter.ItemHomeAdapter.Companion.SPAN_COUNT_GRID
import com.example.awesomeapp.ui.base.BaseActivity
import com.example.awesomeapp.utils.CommonUtils
import javax.inject.Inject
import kotlin.properties.Delegates

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeInterface, ItemHomeAdapter.Listener {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var layoutManager: GridLayoutManager
    @Inject
    lateinit var adapter: ItemHomeAdapter

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var page by Delegates.notNull<Int>()
    private var totalResults by Delegates.notNull<Int>()

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
        adapter.setListener(this, layoutManager)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        initializeData()
    }

    private fun initializeData() {
        page = 1
        totalResults = 0
        viewModel.loadData(page)

        initAdapter()
        initSwipeRefresh()
        initLiveData()
        initRecyclerViewListener()
    }

    private fun initAdapter() {
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun initSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadData(page)
            binding.swipeRefresh.isRefreshing = false
        }

        binding.swipeRefresh.setColorSchemeColors(CommonUtils.getSwipeRefreshColor(this))
    }

    private fun initLiveData() = viewModel.photoLiveData.observe(this, {
        viewModel.photoObservableList.clear()
        viewModel.photoObservableList.addAll(it)
    })

    private fun switchLayout(spanCount: Int) {
        if (layoutManager.spanCount != spanCount) {
            layoutManager.spanCount = spanCount
            adapter.notifyItemRangeChanged(0, adapter.itemCount)
        }
    }

    private fun initRecyclerViewListener() = binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val linearLayoutManager = recyclerView.layoutManager as GridLayoutManager
            val countItem = linearLayoutManager.itemCount
            val lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
            val isLastPosition = countItem.minus(1) == lastVisiblePosition

            if (isLastPosition && page < totalResults) {
                page = page.plus(1)
                viewModel.loadData(page)
            }
        }
    })

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
                switchLayout(SPAN_COUNT_GRID)
                return true
            }
            R.id.action_list -> {
                switchLayout(SPAN_COUNT_LIST)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun itemOnClick(photo: Photo) {

    }

    override fun setTotalResult(totalResults: Int) {
        this.totalResults = totalResults
    }

    companion object {
        private val TAG = HomeActivity::class.java.simpleName
    }
}