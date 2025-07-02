package com.luannguyentrong.minipos.domain.usecase.Payout

import com.luannguyentrong.minipos.domain.model.Payout
import com.luannguyentrong.minipos.domain.reponsitory.PayoutRepository
import javax.inject.Inject

class EditPayoutUseCase @Inject constructor(
    private val repository: PayoutRepository
) {
    suspend operator fun invoke(payout: Payout) {
        return repository.editPayout(payout)
    }
}