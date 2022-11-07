package com.cuvelo.shopfully.test

import com.cuvelo.shopfully.data.repositories.FlyersRepository
import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.usecases.GetFlyersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetFlyersUseCaseTest {

    @RelaxedMockK
    private lateinit var flyerRepository: FlyersRepository

    lateinit var getFlyersUseCase: GetFlyersUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getFlyersUseCase = GetFlyersUseCase(flyerRepository)
    }


    @Test
    fun  `when Api No Return Data Get Flyers From DataBase`() = runBlocking {
        val mockedFlyerList = arrayListOf<FlyerDataDomain>()
        val mockedOneFlyer = FlyerDataDomain(
            "123456",
            "654321",
            "Mocked Flyer",
            false
        )
        mockedFlyerList.add(mockedOneFlyer)

        //Given
        coEvery {
            flyerRepository.getAllFromApi()
        } returns emptyList()

        //When
        val response = getFlyersUseCase()

        //Then
        coVerify(exactly = 1) { flyerRepository.getAllFromDataBase() }
        coVerify(exactly = 0) { flyerRepository.clearFlyersInDataBase() }
        coVerify(exactly = 0) { flyerRepository.insertFlyersInDataBase(mockedFlyerList) }
    }

    @Test
    fun  `when Api Return Data Get Flyers From API and no Read Flyer`() = runBlocking {
        val mockedFlyerList = arrayListOf<FlyerDataDomain>()
        val mockedOneFlyer = FlyerDataDomain(
            "123456",
            "654321",
            "Mocked Flyer",
            false
        )
        mockedFlyerList.add(mockedOneFlyer)

        //Given
        coEvery {
            flyerRepository.getAllFromApi()
        } returns mockedFlyerList

        //When
        val response = getFlyersUseCase()

        //Then
        coVerify(exactly = 1) { flyerRepository.clearFlyersInDataBase() }
        coVerify(exactly = 1) { flyerRepository.insertFlyersInDataBase(mockedFlyerList) }
        assert(mockedFlyerList == response )
        coVerify(exactly = 0) { flyerRepository.getAllFromDataBase() }
    }



}