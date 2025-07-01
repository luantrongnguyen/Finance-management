package com.luannguyentrong.minipos.domain.reponsitory

import com.luannguyentrong.minipos.domain.model.PayoutType

interface PayoutTypeRepository {
    suspend fun getListPayoutType() : List<PayoutType>
    suspend fun getPayoutType(id:Int) : PayoutType
    suspend fun createPayoutType(PayoutType : PayoutType)
    suspend fun editPayoutType(PayoutType: PayoutType)
    suspend fun deletePayoutType(id: Int)
}