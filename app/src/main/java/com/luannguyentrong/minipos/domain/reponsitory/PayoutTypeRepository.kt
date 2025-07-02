package com.luannguyentrong.minipos.domain.reponsitory

import com.luannguyentrong.minipos.domain.model.PayoutType

interface PayoutTypeRepository {
    suspend fun getListPayoutType() : List<PayoutType>
    suspend fun getPayoutType(id:Int) : PayoutType
    suspend fun createPayoutType(payoutType : PayoutType)
    suspend fun editPayoutType(payoutType: PayoutType)
    suspend fun deletePayoutType(id: Int)
}