package com.cxp.gank.data.source

import com.cxp.gank.data.Gank
import com.cxp.gank.data.source.remote.SearchRemoteSource

/**
 * 文 件 名: SearchRepository
 * 创 建 人: CXP
 * 创建日期: 2019-07-25 15:18
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class SearchRepository(private val searchRemoteSource: SearchRemoteSource) : SearchSource  {

    private val pageSize = 20
    private var currentPage = 1

    override fun refreshSearch(queryText: String, callback: SearchSource.SearchCallback) {
        currentPage = 1
        searchRemoteSource.search(queryText, 1, pageSize, callback)
    }

    override fun loadMoreSearch(queryText: String, callback: SearchSource.SearchCallback) {

        searchRemoteSource.search(queryText, currentPage + 1, pageSize, object : SearchSource.SearchCallback {
            override fun onSearchLoaded(gankList: List<Gank>, isEnd: Boolean) {
                currentPage++
                callback.onSearchLoaded(gankList, isEnd)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

        })
    }

    companion object {
        private var INSTANCE: SearchRepository? = null

        @JvmStatic
        fun getInstance(searchRemoteSource: SearchRemoteSource): SearchRepository {
            return INSTANCE ?: SearchRepository(searchRemoteSource).apply {
                INSTANCE = this
            }
        }
    }
}