package com.cuvelo.shopfully.test.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlyersDataModel(
    val id: String,
    @SerializedName("retailer_id") val retailerId: String,
    val title: String) : Parcelable