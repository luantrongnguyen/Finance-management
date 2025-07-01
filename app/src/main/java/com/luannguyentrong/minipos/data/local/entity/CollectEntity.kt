package com.luannguyentrong.minipos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luannguyentrong.minipos.domain.model.Collect

@Entity(tableName = "collects")
data class CollectEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val amount: Double,
    val date: Long
) {
    fun toDomain():Collect = Collect(id,name,amount,date)
    companion object{
        fun fromDomain (collect: Collect) = CollectEntity(collect.id,collect.name,collect.amount,collect.date)
    }
}