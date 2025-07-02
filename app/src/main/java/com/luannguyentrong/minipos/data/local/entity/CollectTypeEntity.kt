package com.luannguyentrong.minipos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luannguyentrong.minipos.domain.model.CollectType

@Entity(tableName = "collect_types")
data class CollectTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name :String,
    val image : String
) {
    fun toDomain() = CollectType(id,name,image)
    companion object{
        fun fromDomain(collectType: CollectType) = CollectTypeEntity(collectType.id, collectType.name, collectType.image)
    }
}