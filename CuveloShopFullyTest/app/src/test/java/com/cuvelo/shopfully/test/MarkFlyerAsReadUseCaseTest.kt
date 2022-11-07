package com.cuvelo.shopfully.test

import com.cuvelo.shopfully.data.repositories.FlyersRepository
import com.cuvelo.shopfully.usecases.MarkFlyerAsReadUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MarkFlyerAsReadUseCaseTest {

    @RelaxedMockK
    private lateinit var flyerRepository: FlyersRepository

    lateinit var markFlyerAsReadUseCase: MarkFlyerAsReadUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        markFlyerAsReadUseCase = MarkFlyerAsReadUseCase(flyerRepository)
    }

    @Test
    fun  `when Detail navigation mark flyer as read`() = runBlocking {
        val mockedFlyerId = "123"

        //Given
        coEvery {
            flyerRepository.markFlyerAsReadById(mockedFlyerId)
        } returns 0

        //When
        val response = markFlyerAsReadUseCase(mockedFlyerId)

        //Then
        coVerify(exactly = 1) { flyerRepository.markFlyerAsReadById(mockedFlyerId) }

    }


}