package com.cxp.gank.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cxp.gank.data.Gank
import com.cxp.gank.ext.getDateString
import com.cxp.gank.ui.common.WebActivity
import kotlinx.android.synthetic.main.recycler_item_gank_data.view.*

/**
 * 文 件 名: GankDataHolder
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 17:17
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankDataHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val onClickListener = View.OnClickListener {
        val gank = it.tag as Gank
        WebActivity.start(it.context, gank.url)
    }

    fun setup(gank: Gank) {
        itemView.tv_desc.text = gank.desc
        itemView.tv_who.text = gank.who
        itemView.tv_date.text = gank.publishedAt.getDateString()
        itemView.item_wrapper.tag = gank
        itemView.item_wrapper.setOnClickListener(onClickListener)
    }
}