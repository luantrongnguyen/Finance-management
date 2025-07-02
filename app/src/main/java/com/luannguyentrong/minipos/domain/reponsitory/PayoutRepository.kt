package com.luannguyentrong.minipos.domain.reponsitory

import com.luannguyentrong.minipos.domain.model.Payout

interface PayoutRepository {
    suspend fun getListPayout() : List<Payout>
    suspend fun getPayout(id:Int) : Payout
    suspend fun createPayout(payout : Payout)
    suspend fun editPayout(payout: Payout)
    suspend fun deletePayout(id: Int)
}