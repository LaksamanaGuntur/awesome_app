package com.example.awesomeapp.ui.activity.home

import androidx.recyclerview.widget.GridLayoutManager
import com.example.awesomeapp.data.model.Photo
import com.example.awesomeapp.ui.activity.home.adapter.ItemHomeAdapter
import com.example.awesomeapp.ui.activity.home.adapter.ItemHomeAdapter.Companion.SPAN_COUNT_GRID
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class HomeModule {
    @Provides
    internal fun provideLayoutManager(activity: HomeActivity): GridLayoutManager = GridLayoutManager(activity, SPAN_COUNT_GRID)

    @Provides
    internal fun provideAdapter(): ItemHomeAdapter = ItemHomeAdapter(ArrayList<Photo>())
}