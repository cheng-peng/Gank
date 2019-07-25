package com.cxp.gank.ui.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.cxp.gank.R
import com.cxp.gank.ext.setupToolBar
import kotlinx.android.synthetic.main.activity_gallery.*

/**
 * 文 件 名: GalleryActivity
 * 创 建 人: CXP
 * 创建日期: 2019-07-23 10:11
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
class GalleryActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_INDEX = "index"
        private const val EXTRA_GANK_LIST = "gank_list"

        @JvmStatic
        fun start(context: Context, index: Int, gankList: ArrayList<String>) {
            val intent = Intent(context, GalleryActivity::class.java)
            intent.putExtra(EXTRA_INDEX, index)
            intent.putStringArrayListExtra(EXTRA_GANK_LIST, gankList)
            context.startActivity(intent)
        }
    }

    private var index: Int = 0
    private lateinit var gankList: List<String>
    private lateinit var pagerAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        if (initData()) {
            setupToolBar(toolbar) {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
            }

            pagerAdapter = GalleryAdapter(gankList)
            view_pager.adapter = pagerAdapter
            view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
//                    title = gankList[position].publishedAt.getDateString()
                    pb_gallery.progress = (((position + 1) / gankList.size.toFloat()) * 100).toInt()
                }

            })
            view_pager.currentItem = index
            pb_gallery.progress = (((index + 1) / gankList.size.toFloat()) * 100).toInt()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData(): Boolean {
        if (!intent.hasExtra(EXTRA_INDEX) || !intent.hasExtra(EXTRA_GANK_LIST)) {
            Toast.makeText(this, R.string.data_error, Toast.LENGTH_SHORT).show()
            finish()
            return false
        }

        index = intent.getIntExtra(EXTRA_INDEX, -1)
        if (index < 0) {
            Toast.makeText(this, R.string.data_error, Toast.LENGTH_SHORT).show()
            finish()
            return false
        }
        gankList = intent.getStringArrayListExtra(EXTRA_GANK_LIST)
        return true
    }
}