package com.luannguyentrong.minipos.domain.usecase.PayoutType

import com.luannguyentrong.minipos.domain.reponsitory.PayoutTypeRepository
import javax.inject.Inject

class DeletePayoutTypeUseCase @Inject constructor(
    private val repository: PayoutTypeRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.deletePayoutType(id)
    }
}