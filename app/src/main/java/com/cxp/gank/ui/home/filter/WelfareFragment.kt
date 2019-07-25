package com.cxp.gank.ui.home.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.cxp.gank.R
import com.cxp.gank.data.Gank
import com.cxp.gank.ui.adapter.LoadMoreListener
import com.cxp.gank.ui.adapter.WelfareAdapter
import com.cxp.gank.ui.adapter.holder.LoadingHolder
import kotlinx.android.synthetic.main.fragment_welfare.*

/**
 * 文 件 名: WelfareFragment
 * 创 建 人: CXP
 * 创建日期: 2019-07-23 9:57
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class WelfareFragment : Fragment(), GankFilterContract.View {

    override lateinit var presenter: GankFilterContract.Presenter

    private var gankList: MutableList<Gank> = mutableListOf()
    private lateinit var welfareAdapter: WelfareAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welfare, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_welfare.layoutManager = GridLayoutManager(context, 2)
        rv_welfare.setHasFixedSize(true)
        welfareAdapter = WelfareAdapter(gankList)
        rv_welfare.adapter = welfareAdapter

        welfareAdapter.loadMoreListener = object : LoadMoreListener {
            override fun loadMore() {
                presenter.loadMore()
            }
        }

        swipe_refresh_layout.setOnRefreshListener {
            presenter.refresh()
        }

        rv_welfare.post {
            swipe_refresh_layout.isRefreshing = true
            presenter.start()
        }
    }

    private fun refreshCompleted() {
        rv_welfare?.post {
            swipe_refresh_layout?.isRefreshing = false
        }
    }

    override fun onRefresh(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.clear()
        this.gankList.addAll(gankList)
        isDataEnd(isEnd)
        welfareAdapter.notifyDataSetChanged()
        refreshCompleted()
    }

    override fun onLoadMore(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.addAll(gankList)
        welfareAdapter.loadMoreCompleted()
        isDataEnd(isEnd)
        welfareAdapter.notifyDataSetChanged()
    }

    override fun refreshGankError() {
        refreshCompleted()
    }

    override fun loadMoreGankError() {
        welfareAdapter.loadMoreCompleted()
        welfareAdapter.loadingStatus = LoadingHolder.STATUS_FAILED
        welfareAdapter.notifyDataSetChanged()
    }

    private fun isDataEnd(isEnd: Boolean) {
        if (isEnd) {
            welfareAdapter.loadingStatus = LoadingHolder.STATUS_END
        } else {
            welfareAdapter.loadingStatus = LoadingHolder.STATUS_LOADING
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = WelfareFragment()
    }
}