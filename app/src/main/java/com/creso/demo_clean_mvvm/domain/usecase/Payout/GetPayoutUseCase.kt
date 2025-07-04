package com.creso.demo_clean_mvvm.domain.usecase.Payout

import com.creso.demo_clean_mvvm.domain.model.Payout
import com.creso.demo_clean_mvvm.domain.reponsitory.PayoutRepository
import javax.inject.Inject

class GetPayoutUseCase @Inject constructor(
    private val repository: PayoutRepository
) {
    suspend operator fun invoke(id: Int): Payout {
        return repository.getPayout(id)
    }
}