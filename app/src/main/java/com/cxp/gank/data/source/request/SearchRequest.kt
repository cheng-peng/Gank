package com.cxp.gank.data.source.request

import com.cxp.gank.data.GankFilterResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.net.URL

/**
 * 文 件 名: SearchRequest
 * 创 建 人: CXP
 * 创建日期: 2019-07-25 15:19
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class SearchRequest(
    private val queryText: String,
    private val page: Int,
    private val count: Int,
    private val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
) : Request<GankFilterResult?> {
    override fun request(): GankFilterResult? {
        val url = ServerConfig.searchApi(queryText, page, count)
        val jsonStr = URL(url).readText()
        try {
            return gson.fromJson(jsonStr, GankFilterResult::class.java)
        } catch (e: Exception) {

        }
        return null
    }
}