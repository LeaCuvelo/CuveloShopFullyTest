package com.cuvelo.shopfully.test.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlyerDataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "retailer_id") val retailerId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "read") val read: Boolean
)