package com.luannguyentrong.minipos.domain.reponsitory

import com.luannguyentrong.minipos.domain.model.Payout

interface PayoutRepository {
    suspend fun getListPayout() : List<Payout>
    suspend fun getPayout(id:Int) : Payout
    suspend fun createPayout(Payout : Payout)
    suspend fun editPayout(Payout: Payout)
    suspend fun deletePayout(id: Int)
}