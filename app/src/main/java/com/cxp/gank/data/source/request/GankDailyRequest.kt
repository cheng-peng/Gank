package com.cxp.gank.data.source.request

import com.cxp.gank.data.GankDailyResult
import com.google.gson.Gson
import java.net.URL

/**
 * 文 件 名: GankDailyRequest
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:20
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankDailyRequest(private val gson: Gson = Gson()) : Request<GankDailyResult?>  {
    override fun request(): GankDailyResult? {
        val url = ServerConfig.gankDailyApi
        val todayJsonStr = URL(url).readText()
        try {
            return gson.fromJson(todayJsonStr, GankDailyResult::class.java)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }
}