package com.cuvelo.shopfully.test.di

import com.cuvelo.shopfully.data.datasources.LocalFlyersDataSource
import com.cuvelo.shopfully.data.datasources.RemoteFlyersDataSource
import com.cuvelo.shopfully.data.repositories.FlyersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideFlyerRepository(localFlyersDataSource: LocalFlyersDataSource,
                               remoteFlyersDataSource: RemoteFlyersDataSource): FlyersRepository{

        return FlyersRepository(localFlyersDataSource, remoteFlyersDataSource)
    }

}