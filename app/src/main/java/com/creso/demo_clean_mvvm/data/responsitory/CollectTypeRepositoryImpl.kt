package com.creso.demo_clean_mvvm.data.responsitory

import com.creso.demo_clean_mvvm.data.local.dao.CollectTypeDao
import com.creso.demo_clean_mvvm.data.local.entity.CollectTypeEntity
import com.creso.demo_clean_mvvm.domain.model.CollectType
import com.creso.demo_clean_mvvm.domain.reponsitory.CollectTypeRepository
import javax.inject.Inject

class CollectTypeRepositoryImpl @Inject constructor(
    private val dao: CollectTypeDao
) : CollectTypeRepository {
    override suspend fun getListCollectType(): List<CollectType> =
        dao.getList().map {
            it.toDomain()
        }

    override suspend fun getCollectType(id: Int): CollectType =
        dao.get(id).toDomain()

    override suspend fun createCollectType(collectType: CollectType) {
        dao.create(CollectTypeEntity.fromDomain(collectType))
    }

    override suspend fun editCollectType(collectType: CollectType) {
        dao.edit(CollectTypeEntity.fromDomain(collectType))
    }

    override suspend fun deleteCollectType(id: Int) {
        dao.delete(id)
    }

}