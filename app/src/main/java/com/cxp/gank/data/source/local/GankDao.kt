package com.cxp.gank.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cxp.gank.data.GankDailyData
import com.cxp.gank.ext.getDateString
import java.util.*

/**
 * 文 件 名: GankDao
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:27
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@Dao
interface GankDao {
    @Query("SELECT * FROM daily WHERE _date = :date")
    fun loadDaily(date: String = Date().getDateString()): GankDailyData?

    @Query("DELETE FROM daily")
    fun deleteDaily()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(daily: GankDailyData)

}