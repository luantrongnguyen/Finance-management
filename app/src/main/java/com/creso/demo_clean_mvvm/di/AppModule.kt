package com.creso.demo_clean_mvvm.di

import android.content.Context
import androidx.room.Room
import com.creso.demo_clean_mvvm.data.local.dao.CollectDao
import com.creso.demo_clean_mvvm.data.local.dao.CollectTypeDao
import com.creso.demo_clean_mvvm.data.local.dao.PayoutDao
import com.creso.demo_clean_mvvm.data.local.dao.PayoutTypeDao
import com.creso.demo_clean_mvvm.data.local.db.FinanceDatabase
import com.creso.demo_clean_mvvm.data.responsitory.CollectRepositoryImpl
import com.creso.demo_clean_mvvm.data.responsitory.CollectTypeRepositoryImpl
import com.creso.demo_clean_mvvm.data.responsitory.PayoutRepositoryImpl
import com.creso.demo_clean_mvvm.data.responsitory.PayoutTypeRepositoryImpl
import com.creso.demo_clean_mvvm.domain.reponsitory.CollectRepository
import com.creso.demo_clean_mvvm.domain.reponsitory.CollectTypeRepository
import com.creso.demo_clean_mvvm.domain.reponsitory.PayoutRepository
import com.creso.demo_clean_mvvm.domain.reponsitory.PayoutTypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FinanceDatabase =
        Room.databaseBuilder(context, FinanceDatabase::class.java, "finance_db").build()

    @Provides
    fun provideCollectDao(db: FinanceDatabase): CollectDao = db.collectDao()

    @Provides
    fun provideCollectRepository(dao: CollectDao): CollectRepository =
        CollectRepositoryImpl(dao)


    @Provides
    fun provideCollectTypeDao(db: FinanceDatabase): CollectTypeDao = db.collectTypeDao()

    @Provides
    fun provideCollectTypeRepository(dao: CollectTypeDao): CollectTypeRepository =
        CollectTypeRepositoryImpl(dao)


    @Provides
    fun providePayoutDao(db: FinanceDatabase): PayoutDao = db.payoutDao()

    @Provides
    fun providePayoutRepository(dao: PayoutDao): PayoutRepository =
        PayoutRepositoryImpl(dao)


    @Provides
    fun providePayoutTypeDao(db: FinanceDatabase): PayoutTypeDao = db.payoutTypeDao()

    @Provides
    fun providePayoutTypeRepository(dao: PayoutTypeDao): PayoutTypeRepository =
        PayoutTypeRepositoryImpl(dao)


}