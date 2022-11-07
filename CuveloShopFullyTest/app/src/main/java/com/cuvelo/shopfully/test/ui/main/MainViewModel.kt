package com.cuvelo.shopfully.test.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.usecases.GetFlyersUseCase
import com.cuvelo.shopfully.usecases.MarkFlyerAsReadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFlyersUseCase: GetFlyersUseCase,
    private val markFlyerAsReadUseCase: MarkFlyerAsReadUseCase) : ViewModel() {

    private val TAG = "MainViewModel"

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    private val _flyers = MutableLiveData<List<FlyerDataDomain>>()
    val flyers get() = _flyers


    fun getFlyers(){
        viewModelScope.launch {
            fetchFlyers()
        }
    }

    private suspend fun fetchFlyers() {
        _progressBarVisibility.value = true

        val result  = getFlyersUseCase.invoke()

        try {
            _flyers.value = result
            _progressBarVisibility.value = false
        } catch (e: Exception) {
            _progressBarVisibility.value = false
            _flyers.value = result
            Log.e(TAG,e.toString())
        }
    }

    fun markAsRead(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            markFlyerAsReadUseCase.invoke(id)
        }
    }

}