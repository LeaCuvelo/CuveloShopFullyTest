package com.cuvelo.shopfully.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _filterIconVisibility = MutableLiveData<Boolean>()
    val filterIconVisibility: LiveData<Boolean> get() = _filterIconVisibility

    fun setFilterIconVisibilityValue(value: Boolean){
        _filterIconVisibility.value = value
    }

    private val _filterIconSelected = MutableLiveData<Boolean>()
    val filterIconSelected: LiveData<Boolean> get() = _filterIconSelected

    fun setFilterIconSelectedValue(value: Boolean){
        _filterIconSelected.value = value
    }

    private val _showReadFilteredFlyers = MutableLiveData<Boolean>()
    val showReadFilteredFlyers: LiveData<Boolean> get() = _showReadFilteredFlyers

    fun setShowReadFilteredFlyersValue(value: Boolean){
        _showReadFilteredFlyers.value = value
    }

}