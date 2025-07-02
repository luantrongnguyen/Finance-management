package com.luannguyentrong.minipos.data.responsitory

import com.luannguyentrong.minipos.data.local.dao.PayoutTypeDao
import com.luannguyentrong.minipos.data.local.entity.PayoutTypeEntity
import com.luannguyentrong.minipos.domain.model.Payout
import com.luannguyentrong.minipos.domain.model.PayoutType
import com.luannguyentrong.minipos.domain.reponsitory.PayoutRepository
import com.luannguyentrong.minipos.domain.reponsitory.PayoutTypeRepository
import javax.inject.Inject

class PayoutTypeRepositoryImpl @Inject constructor(
    private val dao : PayoutTypeDao
): PayoutTypeRepository {
    override suspend fun getListPayoutType(): List<PayoutType> =
        dao.getList().map{
            it.toDomain()
        }
    override suspend fun getPayoutType(id: Int): PayoutType =
        dao.get(id).toDomain()

    override suspend fun createPayoutType(payoutType: PayoutType) {
        dao.create(PayoutTypeEntity.fromDomain(payoutType))
    }

    override suspend fun editPayoutType(payoutType: PayoutType) {
        dao.edit(PayoutTypeEntity.fromDomain(payoutType))
    }

    override suspend fun deletePayoutType(id: Int) {
        dao.delete(id)
    }

}