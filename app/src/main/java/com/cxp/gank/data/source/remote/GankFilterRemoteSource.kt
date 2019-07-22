package com.cxp.gank.data.source.remote

import com.cxp.gank.data.source.GankFilterSource
import com.cxp.gank.data.source.request.GankFilterRequest
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * 文 件 名: GankFilterRemoteSource
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 10:27
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankFilterRemoteSource : GankFilterSource {
    override fun gankFilter(filter: String, page: Int, count: Int, callback: GankFilterSource.LoadGankFilterCallback) {
        doAsync{
            val gankFilterResult = GankFilterRequest(filter, page, count).request()
            uiThread {
                if (gankFilterResult == null) {
                    callback.onDataNotAvailable()
                    return@uiThread
                }

                if (gankFilterResult.error) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onGankFilterLoaded(gankFilterResult.results, gankFilterResult.results.size < count)
                }

            }
        }
    }

    override fun refreshGankList(currentFiltering: String, callback: GankFilterSource.LoadGankFilterCallback) {
    }

    override fun loadMoreGankList(currentFiltering: String, callback: GankFilterSource.LoadGankFilterCallback) {
    }

    companion object {
        private var INSTANCE: GankFilterRemoteSource? = null

        @JvmStatic
        fun getInstance(): GankFilterRemoteSource {
            return INSTANCE ?: GankFilterRemoteSource().apply {
                INSTANCE = this
            }
        }
    }
}