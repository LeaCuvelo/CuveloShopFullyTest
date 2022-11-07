package com.cuvelo.shopfully.test.data

import android.util.Log
import com.cuvelo.shopfully.data.datasources.RemoteFlyersDataSource
import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.test.data.model.FlyersDataModel

class FlyerRemoteServerImpl(private val flyerRemoteServer: FlyerRemoteServer): RemoteFlyersDataSource {

    private val TAG = "FlyerRemoteServerImpl"

    override suspend fun getAllFlyers(): List<FlyerDataDomain> {
        return try{
            val result = flyerRemoteServer.getFlyers()
            result.data.map{ flyer: FlyersDataModel ->
                FlyerDataDomain(flyer.id, flyer.retailerId, flyer.title, false)
            }
        }catch (e: Exception){
            Log.e(TAG, e.toString())
            listOf()
        }
    }
}