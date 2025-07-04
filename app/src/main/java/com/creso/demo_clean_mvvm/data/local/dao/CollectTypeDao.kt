package com.creso.demo_clean_mvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.creso.demo_clean_mvvm.data.local.entity.CollectTypeEntity

@Dao
interface CollectTypeDao {
    @Query("SELECT * FROM COLLECT_TYPES")
    suspend fun getList(): List<CollectTypeEntity>

    @Query("SELECT * FROM COLLECT_TYPES WHERE id = :id")
    suspend fun get(id:Int): CollectTypeEntity

    @Insert
    suspend fun create(collectType: CollectTypeEntity)

    @Update
    suspend fun edit(collectType: CollectTypeEntity)

    @Query("DELETE FROM COLLECT_TYPES WHERE id = :id")
    suspend fun delete(id: Int)

}