package com.luannguyentrong.minipos.domain.usecase.PayoutType

import com.luannguyentrong.minipos.domain.model.PayoutType
import com.luannguyentrong.minipos.domain.reponsitory.PayoutTypeRepository
import javax.inject.Inject

class EditPayoutTypeUseCase @Inject constructor(
    private val repository: PayoutTypeRepository
) {
    suspend operator fun invoke(PayoutType: PayoutType) {
        return repository.editPayoutType(PayoutType)
    }
}