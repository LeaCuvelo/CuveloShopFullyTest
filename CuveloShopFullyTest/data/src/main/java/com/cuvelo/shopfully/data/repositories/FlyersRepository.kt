package com.cuvelo.shopfully.data.repositories

import com.cuvelo.shopfully.data.datasources.LocalFlyersDataSource
import com.cuvelo.shopfully.data.datasources.RemoteFlyersDataSource
import com.cuvelo.shopfully.domain.FlyerDataDomain

class FlyersRepository(private val localFlyersDataSource: LocalFlyersDataSource, private val remoteFlyersDataSource: RemoteFlyersDataSource) {

    suspend fun getAllFromApi(): List<FlyerDataDomain> {
        return remoteFlyersDataSource.getAllFlyers()
    }

    suspend fun getAllFromDataBase(): List<FlyerDataDomain>{
        return localFlyersDataSource.getAll()
    }

    suspend fun insertFlyersInDataBase(flyers: List<FlyerDataDomain>){
        localFlyersDataSource.save(flyers)
    }

    suspend fun clearFlyersInDataBase(){
        localFlyersDataSource.deleteAll()
    }


}