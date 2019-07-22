package com.cxp.gank.data.ui

/**
 * 文 件 名: GankHeaderItem
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 15:53
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
data class GankHeaderItem(val name: String) : GankItem() {
    init {
        type = GankItem.ITEM_HEADER
    }
}