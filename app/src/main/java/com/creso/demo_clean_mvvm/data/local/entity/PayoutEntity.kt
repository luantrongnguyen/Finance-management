package com.creso.demo_clean_mvvm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.creso.demo_clean_mvvm.domain.model.Payout

@Entity(tableName = "payouts")
class PayoutEntity (
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val name : String,
    val amount: Double,
    val date: Long,
) {
    fun toDomain() : Payout = Payout(id,name,amount,date)
    companion object{
        fun fromDomain(payout: Payout) = PayoutEntity(payout.id,payout.name,payout.amount,payout.date)
    }
}