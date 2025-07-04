package com.creso.demo_clean_mvvm.domain.usecase.PayoutType

import com.creso.demo_clean_mvvm.domain.reponsitory.PayoutTypeRepository
import javax.inject.Inject

class DeletePayoutTypeUseCase @Inject constructor(
    private val repository: PayoutTypeRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.deletePayoutType(id)
    }
}