package com.cxp.gank.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * 文 件 名: AppExecutors
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:24
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */

const val THREAD_COUNT = 3

open class AppExecutors  constructor(
    val diskIO: Executor = DiskIOThreadExecutor(),
    val networkIO: Executor = Executors.newFixedThreadPool(THREAD_COUNT),
    val mainThread: Executor = MainThreadExecutor()
){
    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}