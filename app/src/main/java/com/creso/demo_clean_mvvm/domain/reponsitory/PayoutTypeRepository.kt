package com.creso.demo_clean_mvvm.domain.reponsitory

import com.creso.demo_clean_mvvm.domain.model.PayoutType

interface PayoutTypeRepository {
    suspend fun getListPayoutType() : List<PayoutType>
    suspend fun getPayoutType(id:Int) : PayoutType
    suspend fun createPayoutType(payoutType : PayoutType)
    suspend fun editPayoutType(payoutType: PayoutType)
    suspend fun deletePayoutType(id: Int)
}