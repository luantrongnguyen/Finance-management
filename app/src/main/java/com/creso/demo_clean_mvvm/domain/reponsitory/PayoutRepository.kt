package com.creso.demo_clean_mvvm.domain.reponsitory

import com.creso.demo_clean_mvvm.domain.model.Payout

interface PayoutRepository {
    suspend fun getListPayout() : List<Payout>
    suspend fun getPayout(id:Int) : Payout
    suspend fun createPayout(payout : Payout)
    suspend fun editPayout(payout: Payout)
    suspend fun deletePayout(id: Int)
}