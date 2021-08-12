package com.example.shestore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Model.QuantitySizeModel
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.R
import com.example.shestore.Utility.HtmlParser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_cart_item.view.*

class CartAdapter(val itemList : List<WooCommerceItemsDetail>, val quantitySizeData: HashMap<Int, QuantitySizeModel>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_cart_item, parent, false))
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.itemView.cart_card_itemName.text = itemList[position].name
        holder.itemView.cart_card_itemSubName.text = HtmlParser.htmlToSpannedString(itemList[position].short_description).toString()
        holder.itemView.cart_card_itemPrice.text = HtmlParser.htmlToSpannedString(itemList[position].price_html).toString()
        holder.itemView.cart_card_itemRateing.text = itemList[position].average_rating.toString() + " ★"

        // TODO Add condition when no quantity,size related with id found
        holder.itemView.cart_card_quantity.text = "Quantity: " + quantitySizeData[itemList[position].id]!!.quantity.toString()
        holder.itemView.cart_card_size.text = "Size: " + quantitySizeData[itemList[position].id]!!.size

        Picasso.get().load(itemList[position].images[0].src.toUri()).into(holder.itemView.cart_card_itemView)
    }

    override fun getItemCount(): Int = itemList.size
}