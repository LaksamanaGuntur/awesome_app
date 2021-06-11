package com.example.awesomeapp.utils.rx

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.TestScheduler

class AppSchedulerProviderTest constructor(private val testScheduler: TestScheduler) : SchedulerProvider {

    override fun io(): Scheduler {
        return testScheduler
    }

    override fun ui(): Scheduler {
        return testScheduler
    }
}