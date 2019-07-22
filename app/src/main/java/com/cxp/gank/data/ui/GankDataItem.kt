package com.cxp.gank.data.ui

import com.cxp.gank.data.Gank

/**
 * 文 件 名: GankDataItem
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 15:53
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
data class GankDataItem(val gank: Gank) : GankItem()  {
    init {
        type = GankItem.ITEM_DATA
    }
}