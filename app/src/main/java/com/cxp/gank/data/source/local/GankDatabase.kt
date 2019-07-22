package com.cxp.gank.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cxp.gank.data.GankDailyData

/**
 * 文 件 名: GankDatabase
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:30
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@Database(entities = [GankDailyData::class], version = 1)
@TypeConverters(value = [GankConvert::class])
abstract class GankDatabase : RoomDatabase() {

    abstract fun gankDao(): GankDao

    companion object {
        private var INSTANCE: GankDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): GankDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        GankDatabase::class.java, "gank.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }

}