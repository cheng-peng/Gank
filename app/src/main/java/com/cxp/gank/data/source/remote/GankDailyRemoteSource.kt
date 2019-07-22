package com.cxp.gank.data.source.remote

import android.util.Log
import com.cxp.gank.data.GankDailyData
import com.cxp.gank.data.source.GankDailySource
import com.cxp.gank.data.source.request.GankDailyRequest
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * 文 件 名: GankDailyRemoteSource
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:11
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankDailyRemoteSource private constructor() : GankDailySource {

    private val tag = "GankDailyRemoteSource"

    override fun gankDaily(callback: GankDailySource.LoadGankCallback) {
        doAsync {
            val gankResult = GankDailyRequest().request()
            Log.i(tag, "$gankResult")

            uiThread {
                if (gankResult == null) {
                    callback.onDataNotAvailable()
                } else {
                    if (gankResult.error) {
                        callback.onDataNotAvailable()
                    } else {
                        callback.onGankLoaded(gankResult.results)
                    }
                }
            }
        }
    }

    override fun refreshGank() {
    }

    override fun deleteGankDaily() {
    }

    override fun saveGankDaily(gankDailyData: GankDailyData) {
    }

    companion object {
        private var INSTANCE: GankDailyRemoteSource? = null

        @JvmStatic
        fun getInstance(): GankDailyRemoteSource {
            return INSTANCE ?: GankDailyRemoteSource().apply {
                INSTANCE = this
            }
        }
    }
}