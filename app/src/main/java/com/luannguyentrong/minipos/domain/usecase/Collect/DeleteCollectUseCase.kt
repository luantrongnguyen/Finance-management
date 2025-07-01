package com.luannguyentrong.minipos.domain.usecase.Collect

import com.luannguyentrong.minipos.domain.reponsitory.CollectRepository
import javax.inject.Inject

class DeleteCollectUseCase @Inject constructor(
    private val repository: CollectRepository
) {
    suspend operator fun invoke(id: Int) {
        return repository.deleteCollect(id)
    }
}