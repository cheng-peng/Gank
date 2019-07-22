package com.cxp.gank.ui.home.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cxp.gank.R
import com.cxp.gank.data.Gank
import com.cxp.gank.ui.adapter.GankFilterAdapter
import com.cxp.gank.ui.adapter.LoadMoreListener
import com.cxp.gank.ui.adapter.holder.LoadingHolder
import kotlinx.android.synthetic.main.fragment_gank_filter.*

/**
 * 文 件 名: GankFilterFragment
 * 创 建 人: CXP
 * 创建日期: 2019-07-19 10:45
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankFilterFragment : Fragment(), GankFilterContract.View {

    override lateinit var presenter: GankFilterContract.Presenter

    private lateinit var gankFilterAdapter: GankFilterAdapter
    private var gankList: MutableList<Gank> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gank_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_gank_filter.layoutManager = LinearLayoutManager(activity)
        rv_gank_filter.setHasFixedSize(true)
        rv_gank_filter.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        gankFilterAdapter = GankFilterAdapter(gankList)
        rv_gank_filter.adapter = gankFilterAdapter

        gankFilterAdapter.loadMoreListener = object : LoadMoreListener {
            override fun loadMore() {
                presenter.loadMore()
            }
        }

        swipe_refresh_layout.setOnRefreshListener {
            refreshData()
        }

        rv_gank_filter.post {
            swipe_refresh_layout.isRefreshing = true
            refreshData()
        }
    }

    private fun refreshData() {
        presenter.start()
    }

    override fun onRefresh(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.clear()
        this.gankList.addAll(gankList)
        isDataEnd(isEnd)
        gankFilterAdapter.notifyDataSetChanged()
        refreshCompleted()
    }

    override fun onLoadMore(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.addAll(gankList)
        gankFilterAdapter.loadMoreCompleted()
        isDataEnd(isEnd)
        gankFilterAdapter.notifyDataSetChanged()
    }

    override fun refreshGankError() {
        refreshCompleted()
    }

    override fun loadMoreGankError() {
        gankFilterAdapter.loadMoreCompleted()
        gankFilterAdapter.loadingStatus = LoadingHolder.STATUS_FAILED
        gankFilterAdapter.notifyDataSetChanged()
    }



    private fun isDataEnd(isEnd: Boolean) {
        if (isEnd) {
            gankFilterAdapter.loadingStatus = LoadingHolder.STATUS_END
        } else {
            gankFilterAdapter.loadingStatus = LoadingHolder.STATUS_LOADING
        }
    }

    private fun refreshCompleted() {
        rv_gank_filter?.post {
            swipe_refresh_layout?.isRefreshing = false
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GankFilterFragment()
    }


}