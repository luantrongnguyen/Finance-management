package com.creso.demo_clean_mvvm.domain.usecase.CollectType

import com.creso.demo_clean_mvvm.domain.model.CollectType
import com.creso.demo_clean_mvvm.domain.reponsitory.CollectTypeRepository
import javax.inject.Inject

class EditCollectTypeUseCase @Inject constructor(
    private val repository: CollectTypeRepository
) {
    suspend operator fun invoke(collectType: CollectType) {
        return repository.editCollectType(collectType)
    }
}