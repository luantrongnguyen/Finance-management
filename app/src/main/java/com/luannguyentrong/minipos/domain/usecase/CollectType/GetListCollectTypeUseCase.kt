package com.luannguyentrong.minipos.domain.usecase.CollectTypeType

import com.luannguyentrong.minipos.domain.model.CollectType
import com.luannguyentrong.minipos.domain.reponsitory.CollectTypeRepository
import javax.inject.Inject

class GetListCollectTypeUseCase @Inject constructor(
    private val repository: CollectTypeRepository
) {
    suspend operator fun invoke(): List<CollectType> {
        return repository.getListCollectType()
    }
}