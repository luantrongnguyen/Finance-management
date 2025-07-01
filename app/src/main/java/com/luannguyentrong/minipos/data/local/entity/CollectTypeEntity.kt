package com.luannguyentrong.minipos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collect_types")
data class CollectTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name :String,
    val image : String
) {
    fun toDomain() = CollectTypeEntity(id,name,image)
    companion object{
        fun fromDomain(collectTypeEntity: CollectTypeEntity) = CollectTypeEntity(collectTypeEntity.id, collectTypeEntity.name, collectTypeEntity.image)
    }
}