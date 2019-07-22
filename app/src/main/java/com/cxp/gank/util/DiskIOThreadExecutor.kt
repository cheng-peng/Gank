package com.cxp.gank.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * 文 件 名: DiskIOThreadExecutor
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 16:24
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class DiskIOThreadExecutor : Executor {

    private val diskIO = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) { diskIO.execute(command) }
}