package com.cuvelo.shopfully.usecases

import com.cuvelo.shopfully.data.repositories.FlyersRepository

class MarkFlyerAsReadUseCase(private val repository: FlyersRepository) {

    suspend operator fun invoke(id: String): Int {
        return repository.markFlyerAsReadById(id)
    }
}