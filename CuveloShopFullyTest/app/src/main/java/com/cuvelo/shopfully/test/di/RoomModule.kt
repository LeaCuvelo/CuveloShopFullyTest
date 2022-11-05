package com.cuvelo.shopfully.test.di

import android.content.Context
import androidx.room.Room
import com.cuvelo.shopfully.data.datasources.LocalFlyersDataSource
import com.cuvelo.shopfully.test.data.FlyerLocalDataSource
import com.cuvelo.shopfully.test.data.db.FlyerDao
import com.cuvelo.shopfully.test.data.db.ShopFullyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val SHOPFULLY_DATABASE_NAME = "SHOPFULLY_DATABASE"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ShopFullyDataBase::class.java, SHOPFULLY_DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideFlyerDao(db: ShopFullyDataBase) = db.flyerDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(flyerDao: FlyerDao): LocalFlyersDataSource {
        return FlyerLocalDataSource(flyerDao)
    }


}