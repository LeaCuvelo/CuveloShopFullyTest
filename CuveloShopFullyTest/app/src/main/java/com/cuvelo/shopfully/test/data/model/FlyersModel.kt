package com.cuvelo.shopfully.test.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlyersModel(
    val metadata: FlyersMetadataModel,
    val data: List<FlyersDataModel>) : Parcelable