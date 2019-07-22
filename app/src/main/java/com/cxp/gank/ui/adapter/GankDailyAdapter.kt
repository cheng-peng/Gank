package com.cxp.gank.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cxp.gank.R
import com.cxp.gank.data.ui.GankDataItem
import com.cxp.gank.data.ui.GankHeaderItem
import com.cxp.gank.data.ui.GankItem
import com.cxp.gank.ui.adapter.holder.GankDataHolder
import kotlinx.android.synthetic.main.recycler_item_gank_header.view.*

/**
 * 文 件 名: GankDailyAdapter
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 17:13
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankDailyAdapter(private val gankItemList: MutableList<GankItem>) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            GankItem.ITEM_HEADER -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.recycler_item_gank_header,
                        parent, false
                    )
                return GankHeaderHolder(itemView)
            }
            GankItem.ITEM_DATA -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.recycler_item_gank_data,
                        parent, false
                    )
                return GankDataHolder(itemView)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return gankItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return gankItemList[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GankHeaderHolder -> {
                holder.setup(gankItemList[position] as GankHeaderItem)
            }
            is GankDataHolder -> {
                holder.setup((gankItemList[position] as GankDataItem).gank)
            }
        }
    }

    class GankHeaderHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setup(gankHeaderItem: GankHeaderItem) {
            itemView.tv_header.text = gankHeaderItem.name
        }

    }
}