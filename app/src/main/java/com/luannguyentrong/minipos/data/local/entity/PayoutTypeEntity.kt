package com.luannguyentrong.minipos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luannguyentrong.minipos.domain.model.Payout
import com.luannguyentrong.minipos.domain.model.PayoutType

@Entity(tableName = "payout_types")
class PayoutTypeEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String
){
    fun toDomain(): PayoutType = PayoutType(id,name,image)
    companion object{
        fun fromDomain(payoutType: PayoutType) = PayoutTypeEntity(payoutType.id,payoutType.name,payoutType.image)
    }
}