package com.cuvelo.shopfully.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.test.ui.main.MainFragmentViewModel
import com.cuvelo.shopfully.usecases.GetFlyersUseCase
import com.cuvelo.shopfully.usecases.MarkFlyerAsReadUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainFragmentViewModelTest {

    @RelaxedMockK
    private lateinit var  getFlyersUseCase: GetFlyersUseCase

    @RelaxedMockK
    private lateinit var markFlyerAsReadUseCase: MarkFlyerAsReadUseCase

    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        mainFragmentViewModel = MainFragmentViewModel(getFlyersUseCase, markFlyerAsReadUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }


    @Test
    fun `when viewmodel starts get flyers from API`() = runTest {
        //Given
        val mockedFlyerList = arrayListOf<FlyerDataDomain>()
        val mockedOneFlyer = FlyerDataDomain(
            "123456",
            "654321",
            "Mocked Flyer 1",
            false
        )
        val mockedTwoFlyer = FlyerDataDomain(
            "654321",
            "123456",
            "Mocked Flyer 2",
            false
        )
        mockedFlyerList.add(mockedOneFlyer)
        mockedFlyerList.add(mockedTwoFlyer)

        coEvery {
            getFlyersUseCase()

        }returns mockedFlyerList

        //When
        mainFragmentViewModel.getFlyers()

        //Then
        assert(mainFragmentViewModel.flyers.value == mockedFlyerList)

    }

}