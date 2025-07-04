package com.creso.demo_clean_mvvm.domain.usecase.CollectType

import com.creso.demo_clean_mvvm.domain.reponsitory.CollectTypeRepository
import javax.inject.Inject

class DeleteCollectTypeUseCase @Inject constructor(
    private val repository: CollectTypeRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.deleteCollectType(id)
    }
}