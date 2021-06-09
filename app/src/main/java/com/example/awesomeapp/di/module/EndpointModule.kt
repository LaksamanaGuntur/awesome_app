package com.example.awesomeapp.di.module

import com.example.awesomeapp.BuildConfig
import com.example.awesomeapp.di.ApiInfo
import dagger.Module
import dagger.Provides

@Module
class EndpointModule {

    @Provides
    @ApiInfo
    internal fun provideApiEndpoint(): String = BuildConfig.ENDPOINT_API
}