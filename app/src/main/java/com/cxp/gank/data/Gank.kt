package com.cxp.gank.data

import android.os.Parcelable
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

data class GankFilterResult(val error:Boolean,val results:List<Gank>)