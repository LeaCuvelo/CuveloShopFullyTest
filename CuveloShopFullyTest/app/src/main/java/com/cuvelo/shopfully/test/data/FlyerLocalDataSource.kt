package com.cuvelo.shopfully.test.data

import com.cuvelo.shopfully.data.datasources.LocalFlyersDataSource
import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.test.data.db.FlyerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlyerLocalDataSource(private val flyerDao: FlyerDao): LocalFlyersDataSource {

    override suspend fun getAll(): List<FlyerDataDomain> {
        return flyerDao.getAll().map {
            it.toFlyerDataDomain()
        }
    }

    override suspend fun isEmpty(): Boolean  =
        withContext(Dispatchers.IO){ flyerDao.flyerCount() <= 0 }

    override suspend fun save(flyers: List<FlyerDataDomain>) {
        flyerDao.insertAll(flyers.map {
            it.toFlyerDataEntity()
        })
    }

    override suspend fun deleteAll() {
       flyerDao.deleteAll()
    }

}