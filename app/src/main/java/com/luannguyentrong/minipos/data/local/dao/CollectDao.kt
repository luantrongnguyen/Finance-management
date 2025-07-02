package com.luannguyentrong.minipos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.luannguyentrong.minipos.data.local.entity.CollectEntity

@Dao
interface CollectDao {
    @Query("SELECT * FROM COLLECTS")
    suspend fun getList(): List<CollectEntity>

    @Query("SELECT * FROM COLLECTS WHERE id = :id")
    suspend fun get(id:Int): CollectEntity

    @Insert
    suspend fun create(collect:CollectEntity)

    @Update
    suspend fun edit(collect:CollectEntity)

    @Query("DELETE FROM COLLECTS WHERE id = :id")
    suspend fun delete(id: Int)


}