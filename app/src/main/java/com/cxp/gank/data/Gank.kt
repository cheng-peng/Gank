package com.cxp.gank.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cxp.gank.data.ui.GankDataItem
import com.cxp.gank.data.ui.GankHeaderItem
import com.cxp.gank.data.ui.GankItem
import com.cxp.gank.ext.getDateString
import com.cxp.gank.ui.home.GankFilterType
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * 文 件 名: Gank
 * 创 建 人: CXP
 * 创建日期: 2019-07-19 10:36
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@Parcelize
data class Gank(
    val _id: String = "",
    val createdAt: Date = Date(),
    val desc: String = "",
    val images: MutableList<String> = mutableListOf(),
    val publishedAt: Date = Date(),
    val source: String = "",
    val type: String = "",
    val url: String = "",
    val used: Boolean = false,
    val who: String = ""
) : Parcelable

@Entity(tableName = "daily")
data class GankDailyData(
    @ColumnInfo(name = "_date")
    @PrimaryKey
    val date: String = Date().getDateString(),

    @SerializedName("Android")
    @ColumnInfo(name = "android")
    val android: List<Gank> = listOf(),

    @SerializedName("iOS")
    @ColumnInfo(name = "ios")
    val ios: List<Gank> = listOf(),

    @SerializedName("福利")
    @ColumnInfo(name = "welfare")
    val welfare: List<Gank> = listOf(),

    @SerializedName("前端")
    @ColumnInfo(name = "web")
    val web: List<Gank> = listOf(),

    @SerializedName("App")
    @ColumnInfo(name = "app")
    val app: List<Gank> = listOf()
//    @SerializedName("拓展资源") val extra: List<Gank> = listOf(),
//    @SerializedName("瞎推荐") val random: List<Gank> = listOf(),
//    @SerializedName("休息视频") val video: List<Gank> = listOf()
){
    fun toGankUIItem(): MutableList<GankItem> {
        val list = mutableListOf<GankItem>()
        if (android.isNotEmpty()) {
            list.add(GankHeaderItem(GankFilterType.ANDROID))
            for (item in android) {
                list.add(GankDataItem(item))
            }
        }
        if (ios.isNotEmpty()) {
            list.add(GankHeaderItem(GankFilterType.IOS))
            for (item in ios) {
                list.add(GankDataItem(item))
            }
        }
        if (web.isNotEmpty()) {
            list.add(GankHeaderItem(GankFilterType.WEB))
            for (item in web) {
                list.add(GankDataItem(item))
            }
        }
        if (app.isNotEmpty()) {
            list.add(GankHeaderItem(GankFilterType.APP))
            for (item in app) {
                list.add(GankDataItem(item))
            }
        }
        return list
    }
}

data class GankDailyResult(val category: List<String>, val error: Boolean, val results: GankDailyData)
data class GankFilterResult(val error:Boolean,val results:List<Gank>)