package com.cxp.gank.data.source.request

/**
 * 文 件 名: Request
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 11:17
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
interface Request<out T> {
    fun request(): T
}