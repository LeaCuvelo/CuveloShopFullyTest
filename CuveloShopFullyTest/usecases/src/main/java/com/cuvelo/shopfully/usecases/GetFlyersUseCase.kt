package com.cuvelo.shopfully.usecases

import com.cuvelo.shopfully.data.repositories.FlyersRepository
import com.cuvelo.shopfully.domain.FlyerDataDomain

class GetFlyersUseCase(private val repository: FlyersRepository) {

    suspend operator fun invoke(): List<FlyerDataDomain> {

        val flyers = repository.getAllFromApi()
        val readFlyers = repository.getAllReadFlyers()

        return if(flyers.isNotEmpty()){
            val readMap = readFlyers.associateBy { it.id }
            val flyersUpdatedWithReadColumn =  flyers.map { flyer ->
                readMap[flyer.id] ?: flyer
            }
            repository.clearFlyersInDataBase()
            repository.insertFlyersInDataBase(flyersUpdatedWithReadColumn)
            flyersUpdatedWithReadColumn
        }else{
            repository.getAllFromDataBase()
        }
    }

}