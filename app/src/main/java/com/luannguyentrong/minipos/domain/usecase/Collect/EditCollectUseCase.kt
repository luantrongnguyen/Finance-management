package com.luannguyentrong.minipos.domain.usecase.Collect

import com.luannguyentrong.minipos.domain.model.Collect
import com.luannguyentrong.minipos.domain.reponsitory.CollectRepository
import javax.inject.Inject

class EditCollectUseCase @Inject constructor(
    private val repository: CollectRepository
) {
    suspend operator fun invoke(collect: Collect) {
        return repository.editCollect(collect)
    }
}