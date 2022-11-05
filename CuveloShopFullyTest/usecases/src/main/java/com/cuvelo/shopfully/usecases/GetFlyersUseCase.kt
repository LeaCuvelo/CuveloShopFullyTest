package com.cuvelo.shopfully.usecases

import com.cuvelo.shopfully.data.repositories.FlyersRepository
import com.cuvelo.shopfully.domain.FlyerDataDomain

class GetFlyersUseCase(private val repository: FlyersRepository) {

    suspend operator fun invoke(): List<FlyerDataDomain> {
        val flyers = repository.getAllFromApi()

        return if(flyers.isNotEmpty()){
            repository.clearFlyersInDataBase()
            repository.insertFlyersInDataBase(flyers)
            flyers
        }else{
            repository.getAllFromDataBase()
        }
    }

}