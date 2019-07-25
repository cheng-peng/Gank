package com.cxp.gank.data.source.remote

import com.cxp.gank.data.source.SearchSource
import com.cxp.gank.data.source.request.SearchRequest
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * 文 件 名: SearchRemoteSource
 * 创 建 人: CXP
 * 创建日期: 2019-07-25 15:19
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class SearchRemoteSource: SearchSource.Remote  {
    override fun search(queryText: String, page: Int, count: Int, callback: SearchSource.SearchCallback) {
        doAsync {
            val searchResult = SearchRequest(queryText, page, count).request()
            uiThread {
                if (searchResult == null) {
                    callback.onDataNotAvailable()
                    return@uiThread
                }

                if (searchResult.error) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onSearchLoaded(searchResult.results, searchResult.results.size < count)
                }
            }
        }
    }

    companion object {
        private var INSTANCE: SearchRemoteSource? = null

        @JvmStatic
        fun getInstance(): SearchRemoteSource {
            return INSTANCE ?: SearchRemoteSource().apply {
                INSTANCE = this
            }
        }
    }
}