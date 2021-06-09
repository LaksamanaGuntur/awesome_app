package com.example.awesomeapp.di.module

import android.app.Application
import android.content.Context
import com.example.awesomeapp.data.AppDataManager
import com.example.awesomeapp.data.DataManager
import com.example.awesomeapp.utils.rx.AppSchedulerProvider
import com.example.awesomeapp.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager
}