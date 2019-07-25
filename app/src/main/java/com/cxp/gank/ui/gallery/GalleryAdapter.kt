package com.cxp.gank.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cxp.gank.R
import kotlinx.android.synthetic.main.item_gallery.view.*

/**
 * 文 件 名: GalleryAdapter
 * 创 建 人: CXP
 * 创建日期: 2019-07-23 10:41
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GalleryAdapter(private val gankList: List<String>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {

        return gankList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(container.context).inflate(R.layout.item_gallery, container, false)
        val gank = gankList[position]
        Glide.with(container.context)
            .applyDefaultRequestOptions(RequestOptions().apply { centerCrop() })
            .load(gank)
            .into(itemView.photo_view)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View) {
            container.removeView(`object`)
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}