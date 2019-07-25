package com.cxp.gank.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cxp.gank.Injection
import com.cxp.gank.R
import com.cxp.gank.data.Gank
import com.cxp.gank.ext.hideKeyboard
import com.cxp.gank.ui.adapter.GankFilterAdapter
import com.cxp.gank.ui.adapter.LoadMoreListener
import com.cxp.gank.ui.adapter.holder.LoadingHolder
import kotlinx.android.synthetic.main.activity_search.*

/**
 * 文 件 名: SearchActivity
 * 创 建 人: CXP
 * 创建日期: 2019-07-25 17:09
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class SearchActivity : AppCompatActivity(), View.OnClickListener, SearchContract.View {

    override lateinit var presenter: SearchContract.Presenter

    private lateinit var gankFilterAdapter: GankFilterAdapter
    private var gankList: MutableList<Gank> = mutableListOf()

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        presenter = SearchPresenter(Injection.provideSearchRepository(), this)

        rv_search_result.layoutManager = LinearLayoutManager(this)
        rv_search_result.setHasFixedSize(true)
        rv_search_result.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        gankFilterAdapter = GankFilterAdapter(gankList)
        rv_search_result.adapter = gankFilterAdapter

        gankFilterAdapter.loadMoreListener = object : LoadMoreListener {
            override fun loadMore() {
                presenter.loadMoreSearch()
            }
        }

        swipe_refresh_layout.setOnRefreshListener {
            presenter.refreshSearch()
        }

        btn_close.setOnClickListener(this)
        btn_search.setOnClickListener(this)

        edt_search_box.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                refresh()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun refresh() {
        val text = edt_search_box.text.toString().trim()
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, R.string.search_content_empty, Toast.LENGTH_SHORT).show()
            return
        }
        rv_search_result.post {
            swipe_refresh_layout.isRefreshing = true
            hideKeyboard()
            presenter.queryText = text
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_close -> {
                finish()
            }
            R.id.btn_search -> {
                refresh()
            }
        }
    }

    private fun refreshCompleted() {
        rv_search_result?.post {
            swipe_refresh_layout?.isRefreshing = false
        }
    }

    private fun isDataEnd(isEnd: Boolean) {
        if (isEnd) {
            gankFilterAdapter.loadingStatus = LoadingHolder.STATUS_END
        } else {
            gankFilterAdapter.loadingStatus = LoadingHolder.STATUS_LOADING
        }
    }

    override fun onRefreshSearchResult(gankList: List<Gank>, isEnd: Boolean) {
        refreshCompleted()
        this.gankList.clear()
        this.gankList.addAll(gankList)
        isDataEnd(isEnd)
        gankFilterAdapter.notifyDataSetChanged()
    }

    override fun onLoadMoreSearchResult(gankList: List<Gank>, isEnd: Boolean) {
        this.gankList.addAll(gankList)
        gankFilterAdapter.loadMoreCompleted()
        isDataEnd(isEnd)
        gankFilterAdapter.notifyDataSetChanged()
    }

    override fun refreshError() {
        refreshCompleted()
    }

    override fun loadMoreError() {
        gankFilterAdapter.loadMoreCompleted()
        gankFilterAdapter.loadingStatus = LoadingHolder.STATUS_FAILED
        gankFilterAdapter.notifyDataSetChanged()
    }
}