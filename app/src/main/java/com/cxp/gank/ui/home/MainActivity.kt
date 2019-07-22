package com.cxp.gank.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cxp.gank.Injection
import com.cxp.gank.R
import com.cxp.gank.ext.replaceFragmentInActivity
import com.cxp.gank.ext.setupToolBar
import com.cxp.gank.ext.transparentStatusBar
import com.cxp.gank.ui.home.daily.GankDailyFragment
import com.cxp.gank.ui.home.daily.GankDailyPresenter
import com.cxp.gank.ui.home.filter.GankFilterFragment
import com.cxp.gank.ui.home.filter.GankFilterPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar.*
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var gankDailyPresenter: GankDailyPresenter
    private lateinit var gankFilterPresenter: GankFilterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transparentStatusBar()
        setupToolBar(toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }
        setupDrawerLayout()

        val gankDailyFragment = GankDailyFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame, GankDailyFragment.TAG)
        }

        gankDailyPresenter = GankDailyPresenter(
            Injection.provideGankRepository(
                applicationContext
            ), gankDailyFragment
        )

        gankFilterPresenter = GankFilterPresenter(Injection.provideGankFilterRepository(), null)

    }

    private fun setupDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.drawerArrowDrawable.color = Color.BLACK
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener { menuItem ->
            title = menuItem.title
            drawer_layout.closeDrawers()
            when (menuItem.itemId) {
                R.id.menu_today -> {
                    if (null == supportFragmentManager.findFragmentByTag(GankDailyFragment.TAG)) {
                        GankDailyFragment.newInstance().also {
                            gankDailyPresenter.gankDailyView = it
                            it.presenter = gankDailyPresenter
                            replaceFragmentInActivity(it, R.id.contentFrame, GankDailyFragment.TAG)
                        }
                    }
                }
                R.id.menu_android -> {
                    filterChange(GankFilterType.ANDROID)
                }
                R.id.menu_ios -> {
                    filterChange(GankFilterType.IOS)
                }
                R.id.menu_web -> {
                    filterChange(GankFilterType.WEB)
                }
                R.id.menu_app -> {
                    filterChange(GankFilterType.APP)
                }
                R.id.menu_extra -> {
                    filterChange(GankFilterType.EXTRA_SOURCES)
                }
//                R.id.menu_video -> {
//                    filterChange(GankFilterType.VIDEO)
//                }
                R.id.menu_welfare -> {
//                    val welfareFragment = supportFragmentManager.findFragmentByTag(GankFilterType.WELFARE)
//                    if (null == welfareFragment) {
//                        WelfareFragment.newInstance().also {
//                            gankFilterPresenter.gankFilterView = it
//                            it.presenter = gankFilterPresenter
//                            gankFilterPresenter.currentFiltering = GankFilterType.WELFARE
//                            replaceFragmentInActivity(it, R.id.contentFrame, GankFilterType.WELFARE)
//                        }
//                    }
                }
                R.id.menu_about -> {
//                    if (null == supportFragmentManager.findFragmentByTag(AboutFragment.TAG)) {
//                        AboutFragment.newInstance().also {
//                            replaceFragmentInActivity(it, R.id.contentFrame, AboutFragment.TAG)
//                        }
//                    }
                }
            }
            true
        }

        Glide.with(this)
            .applyDefaultRequestOptions(RequestOptions().apply { centerCrop() })
            .load(R.drawable.seb5)
            .into(nav_view.getHeaderView(0).iv_nav_header)
    }

    private fun filterChange(filterType: String) {
        val gankFilterFragment = supportFragmentManager.findFragmentByTag(filterType)
        if (null == gankFilterFragment) {
            GankFilterFragment.newInstance().also {
                gankFilterPresenter.gankFilterView = it
                it.presenter = gankFilterPresenter
                gankFilterPresenter.currentFiltering = filterType
                replaceFragmentInActivity(it, R.id.contentFrame, filterType)
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.search -> {
//                SearchActivity.start(this)
                return true
            }
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
