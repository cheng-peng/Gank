package com.cxp.gank.ui.home.filter

import com.cxp.gank.data.Gank
import com.cxp.gank.data.source.GankFilterRepository
import com.cxp.gank.data.source.GankFilterSource
import com.cxp.gank.ui.home.GankFilterType

/**
 * 文 件 名: GankFilterPresenter
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 10:18
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankFilterPresenter(
    private val gankFilterRepository: GankFilterRepository,
    var gankFilterView: GankFilterContract.View?
) : GankFilterContract.Presenter {

    override var currentFiltering: String = GankFilterType.ANDROID

    override fun refresh() {
        gankFilterRepository.refreshGankList(currentFiltering, refreshCallback)
    }

    override fun loadMore() {
        gankFilterRepository.loadMoreGankList(currentFiltering, loadMoreCallback)
    }

    override fun start() {
        refresh()
    }

    private val refreshCallback = object : GankFilterSource.LoadGankFilterCallback {
        override fun onGankFilterLoaded(gankList: List<Gank>, isEnd: Boolean) {
            gankFilterView?.onRefresh(gankList, isEnd)
        }

        override fun onDataNotAvailable() {
            gankFilterView?.refreshGankError()
        }

    }

    private val loadMoreCallback = object : GankFilterSource.LoadGankFilterCallback {
        override fun onGankFilterLoaded(gankList: List<Gank>, isEnd: Boolean) {
            gankFilterView?.onLoadMore(gankList, isEnd)
        }

        override fun onDataNotAvailable() {
            gankFilterView?.loadMoreGankError()
        }

    }

}