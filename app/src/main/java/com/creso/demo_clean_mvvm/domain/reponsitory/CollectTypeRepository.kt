package com.creso.demo_clean_mvvm.domain.reponsitory

import com.creso.demo_clean_mvvm.domain.model.CollectType

interface CollectTypeRepository {
    suspend fun getListCollectType() : List<CollectType>
    suspend fun getCollectType(id:Int) : CollectType
    suspend fun createCollectType(collectType : CollectType)
    suspend fun editCollectType(collectType: CollectType)
    suspend fun deleteCollectType(id: Int)
}