package com.cuvelo.shopfully.test.di

import com.cuvelo.shopfully.data.datasources.RemoteFlyersDataSource
import com.cuvelo.shopfully.test.data.FlyerRemoteServer
import com.cuvelo.shopfully.test.data.FlyerRemoteServerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String = "https://run.mocky.io/v3/"

    @Provides
    @Singleton
    fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRemoteDateService(retrofit: Retrofit): FlyerRemoteServer =
        retrofit.create(FlyerRemoteServer::class.java)

    @Provides
    fun provideRemoteDataSource(flyerRemoteServer: FlyerRemoteServer ): RemoteFlyersDataSource {
        return FlyerRemoteServerImpl(flyerRemoteServer)
    }

}