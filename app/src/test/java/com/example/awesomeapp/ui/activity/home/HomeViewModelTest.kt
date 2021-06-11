package com.example.awesomeapp.ui.activity.home

import com.example.awesomeapp.data.DataManager
import com.example.awesomeapp.data.model.Response
import com.example.awesomeapp.utils.rx.AppSchedulerProviderTest
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @Mock
    var homeInterface: HomeInterface? = null
    @Mock
    var dataManager: DataManager? = null

    private lateinit var homeViewModel: HomeViewModel
    private var testScheduler: TestScheduler = TestScheduler()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val appSchedulerProvider = AppSchedulerProviderTest(testScheduler)
        homeViewModel = dataManager?.let { HomeViewModel(it, appSchedulerProvider) }!!
        homeInterface?.let { homeViewModel.setNavigator(it) }
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        homeInterface = null
    }

    @Test
    fun testServer() {
        val response = Response()
        doReturn(Single.just(response))
            .`when`(dataManager)?.getCurated(1, 2)
        homeViewModel.loadData(1)
        testScheduler.triggerActions()

        verify(homeInterface)?.setTotalResult(1)
    }

    companion object {
        @BeforeClass
        @Throws(Exception::class)
        fun onlyOnce() {
        }
    }
}