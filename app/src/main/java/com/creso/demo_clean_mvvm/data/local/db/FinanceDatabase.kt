package com.creso.demo_clean_mvvm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.creso.demo_clean_mvvm.data.local.dao.CollectDao
import com.creso.demo_clean_mvvm.data.local.dao.CollectTypeDao
import com.creso.demo_clean_mvvm.data.local.dao.PayoutDao
import com.creso.demo_clean_mvvm.data.local.dao.PayoutTypeDao
import com.creso.demo_clean_mvvm.data.local.entity.CollectEntity
import com.creso.demo_clean_mvvm.data.local.entity.CollectTypeEntity
import com.creso.demo_clean_mvvm.data.local.entity.PayoutEntity
import com.creso.demo_clean_mvvm.data.local.entity.PayoutTypeEntity

@Database(
    entities = [CollectEntity::class, CollectTypeEntity::class, PayoutEntity::class, PayoutTypeEntity::class],
    version = 1,
    exportSchema = false
)

abstract class FinanceDatabase : RoomDatabase() {
    abstract fun collectDao(): CollectDao
    abstract fun collectTypeDao(): CollectTypeDao
    abstract fun payoutTypeDao(): PayoutTypeDao
    abstract fun payoutDao(): PayoutDao
}