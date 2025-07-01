package com.luannguyentrong.minipos.domain.reponsitory

import com.luannguyentrong.minipos.domain.model.Collect
import com.luannguyentrong.minipos.domain.model.CollectType

interface CollectRepository {
    suspend fun getListCollect() : List<Collect>
    suspend fun getCollect(id:Int) : Collect
    suspend fun createCollect(collectType : Collect)
    suspend fun editCollect(collectType: Collect)
    suspend fun deleteCollect(id: Int)
}