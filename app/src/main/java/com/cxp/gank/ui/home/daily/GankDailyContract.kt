package com.cxp.gank.ui.home.daily

import com.cxp.gank.data.GankDailyData
import com.cxp.gank.ui.BasePresenter
import com.cxp.gank.ui.BaseView

/**
 * 文 件 名: GankDailyContract
 * 创 建 人: CXP
 * 创建日期: 2019-07-22 15:49
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
interface GankDailyContract {
    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(active: Boolean)

        fun showGankDaily(gankDailyData: GankDailyData)

        fun showLoadingGankError()
    }

    interface Presenter : BasePresenter {

        fun gankDaily(forceUpdate: Boolean)
    }
}