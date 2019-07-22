package com.cxp.gank.data.source.local

import android.text.TextUtils
import androidx.room.TypeConverter
import com.cxp.gank.data.Gank
import com.google.gson.Gson

/**
 * 文 件 名: GankConvert
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:30
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankConvert {
    companion object {

        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toGankList(json: String): List<Gank> {
            if (TextUtils.isEmpty(json))
                return listOf()

            return gson.fromJson(json, Array<Gank>::class.java).asList()
        }

        @TypeConverter
        @JvmStatic
        fun toGankJsonStr(gankList: List<Gank>): String {
            return gson.toJson(gankList)
        }
    }

}