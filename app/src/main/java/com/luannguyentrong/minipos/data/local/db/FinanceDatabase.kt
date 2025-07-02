package com.luannguyentrong.minipos.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luannguyentrong.minipos.data.local.dao.CollectDao
import com.luannguyentrong.minipos.data.local.dao.CollectTypeDao
import com.luannguyentrong.minipos.data.local.dao.PayoutDao
import com.luannguyentrong.minipos.data.local.dao.PayoutTypeDao
import com.luannguyentrong.minipos.data.local.entity.CollectEntity
import com.luannguyentrong.minipos.data.local.entity.CollectTypeEntity
import com.luannguyentrong.minipos.data.local.entity.PayoutEntity
import com.luannguyentrong.minipos.data.local.entity.PayoutTypeEntity
import com.luannguyentrong.minipos.domain.model.PayoutType

@Database(
    entities = [CollectEntity::class, CollectTypeEntity::class, PayoutEntity::class, PayoutTypeEntity::class],
    version = 1
)

abstract class FinanceDatabase : RoomDatabase() {
    abstract fun collectDao(): CollectDao
    abstract fun collectTypeDao(): CollectTypeDao
    abstract fun payoutTypeDao(): PayoutTypeDao
    abstract fun payoutDao(): PayoutDao
}