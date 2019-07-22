package com.cxp.gank.data.source.local

import com.cxp.gank.data.GankDailyData
import com.cxp.gank.data.source.GankDailySource
import com.cxp.gank.util.AppExecutors

/**
 * 文 件 名: GankDailyLocalSource
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:23
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankDailyLocalSource private constructor(
    private val appExecutors: AppExecutors,
    private val gankDao: GankDao
) : GankDailySource {
    override fun gankDaily(callback: GankDailySource.LoadGankCallback) {
        appExecutors.diskIO.execute {
            val dailyData = gankDao.loadDaily()
            appExecutors.mainThread.execute {
                if (dailyData != null) {
                    callback.onGankLoaded(dailyData)
                }
            }
        }
    }

    override fun refreshGank() {
    }

    override fun deleteGankDaily() {
        appExecutors.diskIO.execute {
            gankDao.deleteDaily()
        }
    }

    override fun saveGankDaily(gankDailyData: GankDailyData) {
        appExecutors.diskIO.execute {
            gankDao.deleteDaily()
            gankDao.insert(gankDailyData)
        }
    }

    companion object {
        private var INSTANCE: GankDailyLocalSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, gankDao: GankDao): GankDailyLocalSource {
            return INSTANCE ?: GankDailyLocalSource(appExecutors, gankDao).apply {
                INSTANCE = this
            }
        }
    }
}