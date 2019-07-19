package com.cxp.gank.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cxp.gank.R
import com.cxp.gank.ui.adapter.LoadMoreListener
import kotlinx.android.synthetic.main.layout_loading_more.view.*

/**
 * 文 件 名: LoadingHolder
 * 创 建 人: CXP
 * 创建日期: 2019-07-19 14:35
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            status = STATUS_LOADING
            failedRetryClickListener?.loadMore()
        }
    }

    var failedRetryClickListener: LoadMoreListener? = null

    var status: Int = STATUS_HIDDEN
        set(value) {
            field = value
            setup(value)
        }

    private fun setup(status: Int) {
        when (status) {
            STATUS_HIDDEN -> {
                itemView.visibility = View.GONE
            }
            STATUS_LOADING -> {
                itemView.visibility = View.VISIBLE
                itemView.loading_layout.visibility = View.VISIBLE
                itemView.load_failed_layout.visibility = View.GONE
                itemView.tv_load_end.visibility = View.GONE
                itemClickable(false)
            }
            STATUS_END -> {
                itemView.visibility = View.VISIBLE
                itemView.loading_layout.visibility = View.GONE
                itemView.load_failed_layout.visibility = View.GONE
                itemView.tv_load_end.visibility = View.VISIBLE
                itemClickable(false)
            }
            STATUS_FAILED -> {
                itemView.visibility = View.VISIBLE
                itemView.loading_layout.visibility = View.GONE
                itemView.load_failed_layout.visibility = View.VISIBLE
                itemView.tv_load_end.visibility = View.GONE
                itemClickable(true)
            }

        }
    }

    private fun itemClickable(clickable: Boolean) {
        if (clickable) {
            itemView.isClickable = true
            itemView.isFocusable = true
        } else {
            itemView.isClickable = false
            itemView.isFocusable = false
        }
    }


    companion object {
        const val STATUS_HIDDEN = -1
        const val STATUS_LOADING = 0
        const val STATUS_FAILED = 1
        const val STATUS_END = 2

        const val LAYOUT_ID = R.layout.layout_loading_more
    }


}