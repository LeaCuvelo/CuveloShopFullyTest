package com.cuvelo.shopfully.test.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlyersMetadataModel(
    val success: Int,
    val code: Int,
    val message: String,
    val time: Float) : Parcelable