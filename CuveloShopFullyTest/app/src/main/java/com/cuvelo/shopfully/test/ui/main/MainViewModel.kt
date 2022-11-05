package com.cuvelo.shopfully.test.ui.main

import com.cuvelo.shopfully.test.ui.BaseViewModel
import com.cuvelo.shopfully.usecases.GetFlyersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFlyersUseCase: GetFlyersUseCase) : BaseViewModel() {




    }
