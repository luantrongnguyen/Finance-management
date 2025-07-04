package com.creso.demo_clean_mvvm.domain.usecase.Collect

import com.creso.demo_clean_mvvm.domain.reponsitory.CollectRepository
import javax.inject.Inject

class DeleteCollectUseCase @Inject constructor(
    private val repository: CollectRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.deleteCollect(id)
    }
}