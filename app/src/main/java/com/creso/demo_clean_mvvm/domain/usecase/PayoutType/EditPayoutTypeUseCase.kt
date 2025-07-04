package com.creso.demo_clean_mvvm.domain.usecase.PayoutType

import com.creso.demo_clean_mvvm.domain.model.PayoutType
import com.creso.demo_clean_mvvm.domain.reponsitory.PayoutTypeRepository
import javax.inject.Inject

class EditPayoutTypeUseCase @Inject constructor(
    private val repository: PayoutTypeRepository
) {
    suspend operator fun invoke(payoutType: PayoutType) {
        return repository.editPayoutType(payoutType)
    }
}