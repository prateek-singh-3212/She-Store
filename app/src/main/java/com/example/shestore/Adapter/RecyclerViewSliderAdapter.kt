package com.example.shestore.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.TabModel
import com.example.shestore.Model.setData
import com.example.shestore.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.viewpager_itemlist_rv.view.*

class RecyclerViewSliderAdapter(val categoryList: List<TabModel>, val viewpager: ViewPager2, val tab: TabLayout, val context: Context, val itemSelected: ItemData) : RecyclerView.Adapter<RecyclerViewSliderAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewSliderAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewpager_itemlist_rv, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewSliderAdapter.ViewHolder, position: Int) {
        holder.itemView.viewpager_itemlist_rv.adapter = context?.let { ItemAdapter(it, setData(), itemSelected) }
        holder.itemView.viewpager_itemlist_rv.layoutManager = GridLayoutManager(context, 2)

        TabLayoutMediator(tab, viewpager) { tab, position ->
            tab.text = categoryList[position].catName
            tab.setIcon(categoryList[position].catImageResId)
        }.attach()
    }

    override fun getItemCount(): Int = categoryList.size
}