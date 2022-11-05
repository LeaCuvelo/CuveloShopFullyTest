package com.cuvelo.shopfully.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [FlyerDataEntity::class],
    version = 1
)
abstract class ShopFullyDataBase : RoomDatabase() {

}