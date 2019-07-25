package com.cxp.gank.ui.home.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cxp.gank.R
import kotlinx.android.synthetic.main.fragment_about.*

/**
 * 文 件 名: AboutFragment
 * 创 建 人: CXP
 * 创建日期: 2019-07-23 9:46
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class AboutFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        about_tv.text="CXP"
    }

    companion object {

        const val TAG = "AboutFragment"

        @JvmStatic
        fun newInstance() = AboutFragment()
    }
}