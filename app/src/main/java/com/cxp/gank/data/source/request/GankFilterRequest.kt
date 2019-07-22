package com.cxp.gank.data.source.request

import com.cxp.gank.data.GankFilterResult
import com.google.gson.Gson
import java.net.URL

/**
 * 文 件 名: GankFilterRequest
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 11:16
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankFilterRequest(
    private val filter: String,
    private val page: Int,
    private val count: Int,
    private val gson: Gson = Gson()
) : Request<GankFilterResult?> {
    override fun request(): GankFilterResult? {
        val url = ServerConfig.gankDataFilterApi(filter, page, count)
        val jsonStr = URL(url).readText()
        try {
            return gson.fromJson(jsonStr, GankFilterResult::class.java)
        } catch (e: Exception) {

        }

        return null
    }

}