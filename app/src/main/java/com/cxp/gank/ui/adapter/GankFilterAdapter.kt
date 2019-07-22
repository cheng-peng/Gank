package com.cxp.gank.ui.adapter

import android.view.View
import com.cxp.gank.R
import com.cxp.gank.data.Gank
import com.cxp.gank.ext.getDateString
import com.cxp.gank.ui.common.WebActivity
import kotlinx.android.synthetic.main.recycler_item_gank_data.view.*

/**
 * 文 件 名: GankFilterAdapter
 * 创 建 人: CXP
 * 创建日期: 2019-07-19 16:04
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankFilterAdapter(
    gankList: MutableList<Gank>
) : BaseAdapter<Gank>(gankList, R.layout.recycler_item_gank_data) {

    private val onClickListener = View.OnClickListener {
        val gank=it.tag as Gank
        WebActivity.start(it.context, gank.url)
    }

    override fun render(itemView: View, data: Gank) {
        itemView.tv_desc.text = data.desc
        itemView.tv_who.text = data.who
        itemView.tv_date.text = data.publishedAt.getDateString()
        itemView.item_wrapper.tag = data
        itemView.item_wrapper.setOnClickListener(onClickListener)
    }

}