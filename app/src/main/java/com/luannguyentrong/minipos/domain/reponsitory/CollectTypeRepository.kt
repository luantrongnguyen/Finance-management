package com.luannguyentrong.minipos.domain.reponsitory

import com.luannguyentrong.minipos.domain.model.CollectType

interface CollectTypeRepository {
    suspend fun getListCollectType() : List<CollectType>
    suspend fun getCollectType(id:Int) : CollectType
    suspend fun createCollectType(collectType : CollectType)
    suspend fun editCollectType(collectType: CollectType)
    suspend fun deleteCollectType(id: Int)
}