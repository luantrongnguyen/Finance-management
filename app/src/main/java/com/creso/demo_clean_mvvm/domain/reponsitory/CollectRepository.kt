package com.creso.demo_clean_mvvm.domain.reponsitory

import com.creso.demo_clean_mvvm.domain.model.Collect

interface CollectRepository {
    suspend fun getListCollect() : List<Collect>
    suspend fun getCollect(id:Int) : Collect
    suspend fun createCollect(collect : Collect)
    suspend fun editCollect(collect: Collect)
    suspend fun deleteCollect(id: Int)
}