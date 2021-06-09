package com.example.awesomeapp.di.component

import android.app.Application
import com.example.awesomeapp.AwesomeApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import com.example.awesomeapp.di.builder.ActivityBuilder
import com.example.awesomeapp.di.module.NetworkModule
import com.example.awesomeapp.di.module.DataModule
import com.example.awesomeapp.di.module.EndpointModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (NetworkModule::class),
    (DataModule::class),
    (EndpointModule::class),
    (ActivityBuilder::class)
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: AwesomeApp)
}