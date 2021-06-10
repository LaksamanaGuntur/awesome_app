package com.example.awesomeapp.di.builder

import com.example.awesomeapp.ui.activity.detail.DetailActivity
import com.example.awesomeapp.ui.activity.home.HomeActivity
import com.example.awesomeapp.ui.activity.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(HomeModule::class)])
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun bindDetailActivity(): DetailActivity
}