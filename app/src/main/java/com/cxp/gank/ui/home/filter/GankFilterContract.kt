package com.cxp.gank.ui.home.filter

import com.cxp.gank.data.Gank
import com.cxp.gank.ui.BasePresenter
import com.cxp.gank.ui.BaseView

/**
 * 文 件 名: GankFilterContract
 * 创 建 人: CXP
 * 创建日期: 2019-07-19 9:55
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
interface GankFilterContract {

    interface View : BaseView<Presenter> {

        fun onRefresh(gankList: List<Gank>, isEnd: Boolean)

        fun onLoadMore(gankList: List<Gank>, isEnd: Boolean)

        fun refreshGankError()

        fun loadMoreGankError()
    }

    interface Presenter : BasePresenter {

        var currentFiltering: String

        fun refresh()

        fun loadMore()

    }
}