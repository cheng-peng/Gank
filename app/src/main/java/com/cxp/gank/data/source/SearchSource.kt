package com.cxp.gank.data.source

import com.cxp.gank.data.Gank

/**
 * 文 件 名: SearchSource
 * 创 建 人: CXP
 * 创建日期: 2019-07-25 15:17
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
interface SearchSource {
    interface SearchCallback {

        fun onSearchLoaded(gankList: List<Gank>, isEnd: Boolean)

        fun onDataNotAvailable()
    }

    interface Remote {

        fun search(queryText: String, page: Int, count: Int, callback: SearchCallback)

    }

    fun refreshSearch(queryText: String, callback: SearchCallback)

    fun loadMoreSearch(queryText: String, callback: SearchCallback)
}