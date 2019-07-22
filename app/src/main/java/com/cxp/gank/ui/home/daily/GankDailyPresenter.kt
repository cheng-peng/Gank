package com.cxp.gank.ui.home.daily

import com.cxp.gank.data.GankDailyData
import com.cxp.gank.data.source.GankDailyRepository
import com.cxp.gank.data.source.GankDailySource

/**
 * 文 件 名: GankDailyPresenter
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 17:08
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GankDailyPresenter(
    private val gankDailyRepository: GankDailyRepository,
    var gankDailyView: GankDailyContract.View?
) : GankDailyContract.Presenter {

    private var firstLoad = true

    init {
        gankDailyView?.presenter = this
    }

    override fun gankDaily(forceUpdate: Boolean) {
        if (forceUpdate || firstLoad) {
            gankDailyRepository.refreshGank()
        }

        firstLoad = false
        gankDailyRepository.gankDaily(object : GankDailySource.LoadGankCallback {

            override fun onGankLoaded(gankDailyData: GankDailyData) {
                gankDailyView?.showGankDaily(gankDailyData)
            }

            override fun onDataNotAvailable() {
                gankDailyView?.showLoadingGankError()
            }

        })

    }

    override fun start() {
        gankDaily(false)
    }
}