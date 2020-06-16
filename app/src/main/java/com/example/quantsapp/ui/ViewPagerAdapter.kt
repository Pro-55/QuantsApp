package com.example.quantsapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = mutableListOf<Pair<String, Fragment>>()

    override fun getItem(position: Int) = fragments[position].second

    override fun getPageTitle(position: Int) = fragments[position].first

    override fun getCount() = fragments.size

    fun addFragments(list: List<Pair<String, Fragment>>) {
        fragments.clear()
        fragments.addAll(list)
    }

}