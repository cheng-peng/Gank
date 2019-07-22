package com.cxp.gank.data.source.local

import com.cxp.gank.data.source.GankFilterSource

/**
 * 文 件 名: GankFilterLocalSource
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 10:22
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankFilterLocalSource : GankFilterSource {

    override fun gankFilter(filter: String, page: Int, count: Int, callback: GankFilterSource.LoadGankFilterCallback) {
    }

    override fun refreshGankList(currentFiltering: String, callback: GankFilterSource.LoadGankFilterCallback) {
    }

    override fun loadMoreGankList(currentFiltering: String, callback: GankFilterSource.LoadGankFilterCallback) {
    }

    companion object {
        private var INSTANCE: GankFilterLocalSource? = null

        @JvmStatic
        fun getInstance(): GankFilterLocalSource {
            return INSTANCE ?: GankFilterLocalSource().apply {
                INSTANCE = this
            }
        }

    }

}