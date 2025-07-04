package com.creso.demo_clean_mvvm.data.responsitory

import com.creso.demo_clean_mvvm.data.local.dao.PayoutDao
import com.creso.demo_clean_mvvm.data.local.entity.PayoutEntity
import com.creso.demo_clean_mvvm.domain.model.Payout
import com.creso.demo_clean_mvvm.domain.reponsitory.PayoutRepository
import javax.inject.Inject

class PayoutRepositoryImpl @Inject constructor(
    private val dao: PayoutDao
): PayoutRepository {
    override suspend fun getListPayout(): List<Payout> =
        dao.getList().map {
            it.toDomain()
        }

    override suspend fun getPayout(id: Int): Payout =
        dao.get(id).toDomain()

    override suspend fun createPayout(payout: Payout) {
        dao.create(PayoutEntity.fromDomain(payout))
    }

    override suspend fun editPayout(payout: Payout) {
        dao.edit(PayoutEntity.fromDomain(payout))
    }

    override suspend fun deletePayout(id: Int) {
        dao.delete(id)
    }
}