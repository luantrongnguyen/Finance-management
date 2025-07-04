package com.creso.demo_clean_mvvm.domain.usecase.Collect

import com.creso.demo_clean_mvvm.domain.model.Collect
import com.creso.demo_clean_mvvm.domain.reponsitory.CollectRepository
import javax.inject.Inject

class EditCollectUseCase @Inject constructor(
    private val repository: CollectRepository
) {
    suspend operator fun invoke(collect: Collect) {
        return repository.editCollect(collect)
    }
}