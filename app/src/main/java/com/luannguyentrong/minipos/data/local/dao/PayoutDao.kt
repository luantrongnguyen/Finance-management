package com.luannguyentrong.minipos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.luannguyentrong.minipos.data.local.entity.PayoutEntity
import com.luannguyentrong.minipos.domain.model.Payout

@Dao
interface PayoutDao {
    @Query("SELECT * FROM PAYOUTS")
    suspend fun getList(): List<PayoutEntity>

    @Query("SELECT * FROM PAYOUTS WHERE id = :id")
    suspend fun get(id:Int): PayoutEntity

    @Insert
    suspend fun create(payout: PayoutEntity)

    @Update
    suspend fun edit(payout: PayoutEntity)

    @Query("DELETE FROM payouts WHERE id = :id")
    suspend fun delete(id: Int)

}