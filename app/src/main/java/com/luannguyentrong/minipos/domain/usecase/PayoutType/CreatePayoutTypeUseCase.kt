package com.luannguyentrong.minipos.domain.usecase.PayoutType

import com.luannguyentrong.minipos.domain.model.PayoutType
import com.luannguyentrong.minipos.domain.reponsitory.PayoutTypeRepository
import javax.inject.Inject

class CreatePayoutTypeUseCase @Inject constructor(
    private val repository: PayoutTypeRepository
) {
    suspend operator fun invoke(payoutType: PayoutType) {
        return repository.createPayoutType(payoutType)
    }
}