package com.creso.demo_clean_mvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.creso.demo_clean_mvvm.data.local.entity.PayoutTypeEntity

@Dao
interface PayoutTypeDao {
    @Query("SELECT * FROM PAYOUT_TYPES")
    suspend fun getList(): List<PayoutTypeEntity>

    @Query("SELECT * FROM PAYOUT_TYPES WHERE id = :id")
    suspend fun get(id:Int): PayoutTypeEntity

    @Insert
    suspend fun create(payout: PayoutTypeEntity)

    @Update
    suspend fun edit(payoutType: PayoutTypeEntity)

    @Query("DELETE FROM payouts WHERE id = :id")
    suspend fun delete(id: Int)

}