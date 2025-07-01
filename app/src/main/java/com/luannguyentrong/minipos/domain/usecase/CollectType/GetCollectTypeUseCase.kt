package com.luannguyentrong.minipos.domain.usecase.CollectTypeType

import com.luannguyentrong.minipos.domain.model.CollectType
import com.luannguyentrong.minipos.domain.reponsitory.CollectTypeRepository
import javax.inject.Inject

class GetCollectTypeUseCase @Inject constructor(
    private val repository: CollectTypeRepository
) {
    suspend operator fun invoke(id: Int): CollectType {
        return repository.getCollectType(id)
    }
}