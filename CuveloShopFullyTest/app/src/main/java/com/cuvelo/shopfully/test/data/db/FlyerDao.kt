package com.cuvelo.shopfully.test.data.db

import androidx.room.*

@Dao
interface FlyerDao {

    @Query("SELECT COUNT(id) FROM FlyerDataEntity")
    suspend fun flyerCount(): Int

    @Query("SELECT * FROM FlyerDataEntity")
    suspend fun getAll(): List<FlyerDataEntity>

    @Query("SELECT * FROM FlyerDataEntity WHERE id = :id")
    suspend fun getById(id: Int): FlyerDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flyer: FlyerDataEntity)

    @Insert
    suspend fun insertAll(flyer: List<FlyerDataEntity>)

    @Delete()
    suspend fun deleteById(flyer: FlyerDataEntity)

    @Query("DELETE FROM FlyerDataEntity ")
    suspend fun deleteAll()

}