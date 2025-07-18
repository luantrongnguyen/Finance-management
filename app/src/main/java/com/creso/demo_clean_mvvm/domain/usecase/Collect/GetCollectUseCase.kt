package com.creso.demo_clean_mvvm.domain.usecase.Collect

import com.creso.demo_clean_mvvm.domain.model.Collect
import com.creso.demo_clean_mvvm.domain.reponsitory.CollectRepository
import javax.inject.Inject

class GetCollectUseCase @Inject constructor(
    private val repository: CollectRepository
) {
    suspend operator fun invoke(id: Int): Collect {
        return repository.getCollect(id)
    }
}