package com.cxp.gank.data.source

import com.cxp.gank.data.Gank

/**
 * 文 件 名: GankFilterSource
 * 创 建 人: CXP
 * 创建日期: 2019-07-19 17:22
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
interface GankFilterSource {

    interface LoadGankFilterCallback {
        fun onGankFilterLoaded(gankList: List<Gank>, isEnd: Boolean)

        fun onDataNotAvailable()
    }

    fun gankFilter(filter: String, page: Int, count: Int, callback: LoadGankFilterCallback)

    fun refreshGankList(currentFiltering: String, callback: LoadGankFilterCallback)

    fun loadMoreGankList(currentFiltering: String, callback: LoadGankFilterCallback)
}