package com.cxp.gank

import android.content.Context
import com.cxp.gank.data.source.GankDailyRepository
import com.cxp.gank.data.source.GankFilterRepository
import com.cxp.gank.data.source.SearchRepository
import com.cxp.gank.data.source.local.GankDailyLocalSource
import com.cxp.gank.data.source.local.GankDatabase
import com.cxp.gank.data.source.local.GankFilterLocalSource
import com.cxp.gank.data.source.remote.GankDailyRemoteSource
import com.cxp.gank.data.source.remote.GankFilterRemoteSource
import com.cxp.gank.data.source.remote.SearchRemoteSource
import com.cxp.gank.util.AppExecutors

/**
 * 文 件 名: Injection
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 14:50
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
object Injection {

    fun provideGankRepository(context: Context): GankDailyRepository {
        val database = GankDatabase.getInstance(context)
        return GankDailyRepository.getInstance(
            GankDailyRemoteSource.getInstance(),
            GankDailyLocalSource.getInstance(AppExecutors(), database.gankDao())
        )
    }

    //初始化
    fun provideGankFilterRepository(): GankFilterRepository {
        return GankFilterRepository.getInstance(
            GankFilterRemoteSource.getInstance(),
            GankFilterLocalSource.getInstance()
        )
    }
    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(SearchRemoteSource.getInstance())
    }
}