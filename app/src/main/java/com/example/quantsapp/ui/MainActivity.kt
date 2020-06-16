package com.example.quantsapp.ui

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.quantsapp.R
import com.example.quantsapp.databinding.ActivityMainBinding
import com.example.quantsapp.ui.feed.FeedFragment
import com.example.quantsapp.ui.webview.WebViewFragment

class MainActivity : FragmentActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    //Global
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val colorBackground = resources.getColor(R.color.color_background)
            window.statusBarColor = colorBackground
            window.navigationBarColor = colorBackground
            val isNightMode =
                when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> true
                    else -> false
                }
            if (!isNightMode) {
                window.decorView.systemUiVisibility =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewPager()

    }

    private fun setupViewPager() {

        binding.pagerMain.adapter = ViewPagerAdapter(supportFragmentManager).apply {
            val feedPair = Pair("Feed", FeedFragment.newInstance())
            val webViewPair = Pair("Web View", WebViewFragment.newInstance())
            addFragments(listOf(feedPair, webViewPair))
        }

        binding.tabsMain.setupWithViewPager(binding.pagerMain)

    }
}