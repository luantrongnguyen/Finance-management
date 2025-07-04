package com.creso.demo_clean_mvvm.domain.usecase.Payout

import com.creso.demo_clean_mvvm.domain.reponsitory.PayoutRepository
import javax.inject.Inject

class DeletePayoutUseCase @Inject constructor(
    private val repository: PayoutRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.deletePayout(id)
    }
}