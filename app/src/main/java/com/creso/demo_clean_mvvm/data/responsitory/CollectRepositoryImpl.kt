package com.creso.demo_clean_mvvm.data.responsitory

import com.creso.demo_clean_mvvm.data.local.dao.CollectDao
import com.creso.demo_clean_mvvm.data.local.entity.CollectEntity
import com.creso.demo_clean_mvvm.domain.model.Collect
import com.creso.demo_clean_mvvm.domain.reponsitory.CollectRepository
import javax.inject.Inject

class CollectRepositoryImpl @Inject constructor(
    private val dao: CollectDao
) : CollectRepository {
    override suspend fun getListCollect(): List<Collect> =
        dao.getList()
            .map {
                it.toDomain()
            }


    override suspend fun getCollect(id: Int): Collect =
        dao.get(id).toDomain()


    override suspend fun createCollect(collect: Collect) {
        dao.create(CollectEntity.fromDomain(collect))
    }

    override suspend fun editCollect(collect: Collect) {
        dao.edit(CollectEntity.fromDomain(collect))
    }

    override suspend fun deleteCollect(id: Int) {
        dao.delete(id)
    }
}