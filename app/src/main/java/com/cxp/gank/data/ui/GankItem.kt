package com.cxp.gank.data.ui

/**
 * 文 件 名: GankItem
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 15:51
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
open class GankItem {

    companion object{
        const val ITEM_HEADER = 0
        const val ITEM_DATA = 1
    }

    open var type: Int = ITEM_HEADER
}