package com.luannguyentrong.minipos.domain.usecase.Payout

import com.luannguyentrong.minipos.domain.model.Payout
import com.luannguyentrong.minipos.domain.model.PayoutType
import com.luannguyentrong.minipos.domain.reponsitory.PayoutRepository
import com.luannguyentrong.minipos.domain.reponsitory.PayoutTypeRepository
import javax.inject.Inject


class GetListPayoutUseCase @Inject constructor(
    private val repository: PayoutTypeRepository
) {
    suspend operator fun invoke(): List<PayoutType> {
        return repository.getListPayoutType()
    }
}