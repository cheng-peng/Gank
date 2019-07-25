package com.cxp.gank.ui.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cxp.gank.R
import com.cxp.gank.data.Gank
import com.cxp.gank.ui.gallery.GalleryActivity
import kotlinx.android.synthetic.main.recycler_item_welfare.view.*

/**
 * 文 件 名: WelfareAdapter
 * 创 建 人: CXP
 * 创建日期: 2019-07-23 9:59
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class WelfareAdapter(
    val gankList: MutableList<Gank>
) : BaseAdapter<Gank>(gankList, R.layout.recycler_item_welfare) {


    override fun render(itemView: View, data: Gank) {
        Glide.with(itemView.context)
            .applyDefaultRequestOptions(RequestOptions().apply {
                centerCrop()
            })
            .load(data.url)
            .into(itemView.iv_welfare)
        itemView.tag = data
        itemView.setOnClickListener(onclickListener)
    }

    private val onclickListener = View.OnClickListener {
        if (gankList.isNullOrEmpty()) {
            return@OnClickListener
        }
        val gank = it.tag as Gank
        val position = gankList.indexOf(gank)
        GalleryActivity.start(it.context, position, gankList.map { gank -> gank.url } as ArrayList<String>)
    }

}