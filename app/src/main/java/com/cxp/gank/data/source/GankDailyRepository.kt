package com.cxp.gank.data.source

import com.cxp.gank.data.GankDailyData
import com.cxp.gank.data.source.local.GankDailyLocalSource
import com.cxp.gank.data.source.remote.GankDailyRemoteSource

/**
 * 文 件 名: GankDailyRepository
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:42
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankDailyRepository private constructor(
    private val gankDailyRemoteSource: GankDailyRemoteSource,
    private val gankDailyLocalSource: GankDailyLocalSource
) : GankDailySource {

    private var cacheDailyData: GankDailyData? = null // memory cache
    private var cacheIsDirty = false


    override fun gankDaily(callback: GankDailySource.LoadGankCallback) {
        if (cacheDailyData == null) {
            // no memory cache, try to load db cache first
            getLocalGankDaily(callback)
        } else {
            if (!cacheIsDirty) {
                callback.onGankLoaded(cacheDailyData!!)
                return
            }
        }

        if (cacheIsDirty) {
            getRemoteGankDaily(callback)
        } else {
            getLocalGankDaily(callback)
        }
    }

    override fun refreshGank() {
        cacheIsDirty = true
    }

    private fun getLocalGankDaily(callback: GankDailySource.LoadGankCallback) {
        gankDailyLocalSource.gankDaily(object : GankDailySource.LoadGankCallback {
            override fun onGankLoaded(gankDailyData: GankDailyData) {
                refreshCache(gankDailyData)
                callback.onGankLoaded(gankDailyData)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

        })
    }

    private fun getRemoteGankDaily(callback: GankDailySource.LoadGankCallback) {
        gankDailyRemoteSource.gankDaily(object : GankDailySource.LoadGankCallback {

            override fun onGankLoaded(gankDailyData: GankDailyData) {
                refreshCache(gankDailyData)
                saveGankDaily(gankDailyData)
                callback.onGankLoaded(gankDailyData)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

        })
    }

    private fun refreshCache(gankDailyData: GankDailyData) {
        cacheDailyData = gankDailyData
        cacheIsDirty = false
    }

    override fun deleteGankDaily() {
        gankDailyLocalSource.deleteGankDaily()
    }

    override fun saveGankDaily(gankDailyData: GankDailyData) {
        gankDailyLocalSource.saveGankDaily(gankDailyData)
    }

    companion object {
        private var INSTANCE: GankDailyRepository? = null

        @JvmStatic
        fun getInstance(
            gankDataRemoteSource: GankDailyRemoteSource,
            gankDataLocalSource: GankDailyLocalSource
        ): GankDailyRepository {
            return INSTANCE ?: GankDailyRepository(gankDataRemoteSource, gankDataLocalSource).apply {
                INSTANCE = this
            }
        }
    }
}