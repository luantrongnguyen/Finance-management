package com.luannguyentrong.minipos.domain.usecase.CollectTypeType

import com.luannguyentrong.minipos.domain.reponsitory.CollectTypeRepository
import javax.inject.Inject

class DeleteCollectTypeUseCase @Inject constructor(
    private val repository: CollectTypeRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.deleteCollectType(id)
    }
}