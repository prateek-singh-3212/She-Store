package com.example.shestore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Model.itemList
import com.example.shestore.R
import kotlinx.android.synthetic.main.cardview_itemdetail_header.view.*

class ItemDetailAdapter(val itemDetail : List<itemList>) : RecyclerView.Adapter<ItemDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemDetailAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_itemdetail_header, parent, false))
    }

    override fun onBindViewHolder(holder: ItemDetailAdapter.ViewHolder, position: Int) {
        holder.itemView.cardview_itemDetail_title.text = itemDetail[position].itemName
        holder.itemView.cardview_itemDetail_subname.text = itemDetail[position].itemSubName
        holder.itemView.cardview_itemDetail_price.text = itemDetail[position].price.toString()
    }

    override fun getItemCount(): Int = itemDetail.size
}