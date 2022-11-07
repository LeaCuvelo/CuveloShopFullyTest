package com.cuvelo.shopfully.test.data

import com.cuvelo.shopfully.test.data.model.FlyersModel
import retrofit2.http.GET

interface FlyerRemoteServer {

    @GET("94da1ce3-3d3f-414c-8857-da813df3bb05")
    suspend fun getFlyers() : FlyersModel

}