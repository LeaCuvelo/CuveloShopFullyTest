package com.cuvelo.shopfully.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuvelo.shopfully.data.repositories.NetworkResult
import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.test.ui.BaseViewModel
import com.cuvelo.shopfully.usecases.GetFlyersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFlyersUseCase: GetFlyersUseCase) : BaseViewModel() {

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    private val _errorStateVisibility = MutableLiveData<Boolean>()
    val errorStateVisibility: LiveData<Boolean> get() = _errorStateVisibility

    private val _flyers = MutableLiveData<NetworkResult<List<FlyerDataDomain>>>()
    val flyers get() = _flyers


    init {
        viewModelScope.launch {
            fetchFlyers()
        }
    }

    private suspend fun fetchFlyers() {
        _flyers.value = NetworkResult.Loading()
        _progressBarVisibility.value = true
        _errorStateVisibility.value = false
        try {
            _flyers.value = NetworkResult.Success(getFlyersUseCase.invoke())
            _progressBarVisibility.value = false
            _errorStateVisibility.value = false
        } catch (e: Exception) {
            _progressBarVisibility.value = false
            _errorStateVisibility.value = true
            Timber.e(e)
            _flyers.value = NetworkResult.Failure(e.message.toString())
        }
    }

    fun onFlyerClicked(flyerTitle: String) {
        Timber.d("onFlyerClicked flyerTitle: $flyerTitle")

    }



}