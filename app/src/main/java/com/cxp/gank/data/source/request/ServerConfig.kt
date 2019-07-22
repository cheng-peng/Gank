package com.cxp.gank.data.source.request

/**
 * 文 件 名: ServerConfig
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 11:28
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
object ServerConfig {

    private const val host = "http://gank.io/api"

    const val gankDailyApi = "$host/today"

    fun gankDataFilterApi(filterType: String, page: Int, count: Int = 20): String {
        return "$host/data/$filterType/$count/$page"
    }

    fun searchApi(queryText: String, page: Int, count: Int=20): String {
        return "$host/search/query/$queryText/category/all/count/$count/page/$page"
    }
}