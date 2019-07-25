package com.cxp.gank.ui.search

import com.cxp.gank.data.Gank
import com.cxp.gank.ui.BasePresenter
import com.cxp.gank.ui.BaseView

/**
 * 文 件 名: SearchContract
 * 创 建 人: CXP
 * 创建日期: 2019-07-25 15:16
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
interface SearchContract {

    interface View : BaseView<Presenter> {

        fun onRefreshSearchResult(gankList: List<Gank>, isEnd: Boolean)

        fun onLoadMoreSearchResult(gankList: List<Gank>, isEnd: Boolean)

        fun refreshError()

        fun loadMoreError()
    }

    interface Presenter : BasePresenter {

        var queryText: String

        fun refreshSearch()

        fun loadMoreSearch()
    }
}