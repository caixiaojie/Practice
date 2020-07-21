package com.yanyu.practice.banner.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.yanyu.practice.banner.adapter.AdapterFragmentPager
import com.google.android.material.navigation.NavigationView
import com.yanyu.practice.R
import kotlinx.android.synthetic.main.activity_main_banner.*


class BannerMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_banner)
        initView()
        initData()
        setListener()
    }

    private fun initView() {
        toolbar.apply {
            title = getString(R.string.app_name)
            setSupportActionBar(toolbar)
        }
        drawerLayout.apply {
            val toggle = ActionBarDrawerToggle(
                    this@BannerMainActivity,
                    this,
                    toolbar
                    , R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close)
            addDrawerListener(toggle)
            toggle.syncState()
        }
        nav_view.apply {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
        }
    }

    private fun initData() {
        with(vp_fragment) {
            adapter = AdapterFragmentPager(this@BannerMainActivity)
            isUserInputEnabled = false
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    vp_fragment.isUserInputEnabled = true
                    rg_tab?.check(getCheckedId(position))
                }
            })
        }
    }

    private fun getCheckedId(position: Int): Int {
        return when (position) {
            0 -> {
                vp_fragment.isUserInputEnabled = false
                R.id.rb_home
            }
            1 -> R.id.rb_add
            2 -> R.id.rb_find
            3 -> R.id.rb_others
            else -> R.id.rb_home
        }
    }

    private fun setListener() {
        rg_tab?.setOnCheckedChangeListener { _, checkedId ->
            vp_fragment.isUserInputEnabled = true
            when (checkedId) {
                R.id.rb_home -> {
                    vp_fragment.isUserInputEnabled = false
                    vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_HOME, true)
                }
                R.id.rb_add -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_FIND, true)
                R.id.rb_find -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_INDICATOR, true)
                R.id.rb_others -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_OTHERS, true)
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BannerMainActivity::class.java))
        }
    }

    private val onDrawerNavigationItemSelectedListener =
            NavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_banner -> {
                        WebViewActivity.start(this@BannerMainActivity, getString(R.string.app_name), "https://github.com/zhpanvip/BannerViewPager")
                    }

                    R.id.nav_indicator -> {
                        WebViewActivity.start(this@BannerMainActivity, getString(R.string.indicator_name), "https://github.com/zhpanvip/ViewPagerIndicator")
                    }
                }
                true
            }
}
