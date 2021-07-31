package com.example.shestore.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shestore.Model.TabModel
import com.example.shestore.fragment.CardViewerFragment

class FragmentSliderAdapter(fragment: Fragment, private val tabData: List<TabModel>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = tabData.size

    override fun createFragment(position: Int): Fragment = CardViewerFragment(tabData[position].categoryEndpointURL)
}