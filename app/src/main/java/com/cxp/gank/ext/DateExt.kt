package com.cxp.gank.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * 文 件 名: DateExt
 * 创 建 人: CXP
 * 创建日期: 2019-07-19 10:31
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */

//格式化日期
fun Date.getDateString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    return sdf.format(this)
}