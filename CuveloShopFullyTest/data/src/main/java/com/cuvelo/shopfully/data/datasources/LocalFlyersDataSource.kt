package com.cuvelo.shopfully.data.datasources

import com.cuvelo.shopfully.domain.FlyerDataDomain

interface LocalFlyersDataSource {
    suspend fun getAll(): List<FlyerDataDomain>
    suspend fun isEmpty(): Boolean
    suspend fun save(flyers: List<FlyerDataDomain>)
    suspend fun deleteAll()
}