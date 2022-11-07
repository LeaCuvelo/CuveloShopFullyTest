package com.cuvelo.shopfully.test.data

import com.cuvelo.shopfully.data.datasources.RemoteFlyersDataSource
import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.test.data.model.FlyersDataModel

class FlyerRemoteServerImpl(private val flyerRemoteServer: FlyerRemoteServer): RemoteFlyersDataSource {

    override suspend fun getAllFlyers(): List<FlyerDataDomain> {
        val result = flyerRemoteServer.getFlyers()

        return result.data.map{ flyer: FlyersDataModel ->
            FlyerDataDomain(flyer.id, flyer.retailerId, flyer.title, false)
        }
    }
}