package com.luannguyentrong.minipos.domain.reponsitory

import com.luannguyentrong.minipos.domain.model.Collect
import com.luannguyentrong.minipos.domain.model.CollectType

interface CollectRepository {
    suspend fun getListCollect() : List<Collect>
    suspend fun getCollect(id:Int) : Collect
    suspend fun createCollect(collect : Collect)
    suspend fun editCollect(collect: Collect)
    suspend fun deleteCollect(id: Int)
}