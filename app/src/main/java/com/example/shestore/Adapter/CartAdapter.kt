package com.example.shestore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Model.itemList
import com.example.shestore.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_cart_item.view.*

class CartAdapter(val itemList : List<itemList>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_cart_item, parent, false))
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.itemView.cart_card_itemName.text = itemList[position].itemName
        holder.itemView.cart_card_itemSubName.text = itemList[position].itemSubName
        holder.itemView.cart_card_itemPrice.text = " ₹${itemList[position].price.toString()}"

        Picasso.get().load(itemList[position].imageUrl?.toUri()).into(holder.itemView.cart_card_itemView)
    }

    override fun getItemCount(): Int = itemList.size
}