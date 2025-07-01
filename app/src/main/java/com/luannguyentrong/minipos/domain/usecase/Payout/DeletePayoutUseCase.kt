package com.luannguyentrong.minipos.domain.usecase.Payout

import com.luannguyentrong.minipos.domain.reponsitory.PayoutRepository
import javax.inject.Inject

class DeletePayoutUseCase @Inject constructor(
    private val repository: PayoutRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.deletePayout(id)
    }
}