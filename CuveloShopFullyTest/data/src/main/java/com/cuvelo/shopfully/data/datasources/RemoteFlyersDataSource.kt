package com.cuvelo.shopfully.data.datasources

import com.cuvelo.shopfully.domain.FlyerDataDomain

interface RemoteFlyersDataSource {
    suspend fun getAllFlyers(): List<FlyerDataDomain>
}