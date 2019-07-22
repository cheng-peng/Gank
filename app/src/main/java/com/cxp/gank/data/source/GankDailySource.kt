package com.cxp.gank.data.source

import com.cxp.gank.data.GankDailyData

/**
 * 文 件 名: GankDailySource
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:04
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
interface GankDailySource {

    interface LoadGankCallback {

        fun onGankLoaded(gankDailyData: GankDailyData)

        fun onDataNotAvailable()
    }

    fun gankDaily(callback: LoadGankCallback)

    fun refreshGank()

    fun deleteGankDaily()

    fun saveGankDaily(gankDailyData: GankDailyData)

}