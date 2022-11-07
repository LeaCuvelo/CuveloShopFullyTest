package com.cuvelo.shopfully.test.di

import com.cuvelo.shopfully.data.repositories.FlyersRepository
import com.cuvelo.shopfully.usecases.GetFlyersUseCase
import com.cuvelo.shopfully.usecases.MarkFlyerAsReadUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainViewModelModule {

    @Provides
    fun provideGeFlyersUseCase(repository: FlyersRepository): GetFlyersUseCase {
        return GetFlyersUseCase(repository)
    }

    @Provides
    fun provideMarkFlyerAsReadUseCase(repository: FlyersRepository): MarkFlyerAsReadUseCase {
        return MarkFlyerAsReadUseCase(repository)
    }
}