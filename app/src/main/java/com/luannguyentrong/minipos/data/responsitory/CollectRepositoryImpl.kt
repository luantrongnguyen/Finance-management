package com.luannguyentrong.minipos.data.responsitory

import com.luannguyentrong.minipos.data.local.dao.CollectDao
import com.luannguyentrong.minipos.data.local.entity.CollectEntity
import com.luannguyentrong.minipos.domain.model.Collect
import com.luannguyentrong.minipos.domain.reponsitory.CollectRepository
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