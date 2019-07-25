package com.cxp.gank.ui.search

import com.cxp.gank.data.Gank
import com.cxp.gank.data.source.SearchRepository
import com.cxp.gank.data.source.SearchSource

/**
 * 文 件 名: SearchPresenter
 * 创 建 人: CXP
 * 创建日期: 2019-07-25 17:05
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class SearchPresenter(
    private val searchRepository: SearchRepository,
    private val searchView: SearchContract.View
) : SearchContract.Presenter {
    override var queryText: String = ""
        set(value) {
            field=value
            refreshSearch()
        }

    override fun refreshSearch() {
        searchRepository.refreshSearch(queryText,refreshCallback)
    }

    private val refreshCallback = object : SearchSource.SearchCallback {
        override fun onSearchLoaded(gankList: List<Gank>, isEnd: Boolean) {
            searchView.onRefreshSearchResult(gankList, isEnd)
        }

        override fun onDataNotAvailable() {
            searchView.refreshError()
        }

    }


    override fun loadMoreSearch() {
        searchRepository.loadMoreSearch(queryText, loadMoreCallback)
    }

    private val loadMoreCallback = object : SearchSource.SearchCallback {
        override fun onSearchLoaded(gankList: List<Gank>, isEnd: Boolean) {
            searchView.onLoadMoreSearchResult(gankList, isEnd)
        }

        override fun onDataNotAvailable() {
            searchView.loadMoreError()
        }

    }


    override fun start() {
    }



}