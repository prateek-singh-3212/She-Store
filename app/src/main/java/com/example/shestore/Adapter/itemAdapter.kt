package com.example.shestore.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.itemList
import com.example.shestore.R
import com.example.shestore.fragment.itemDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_item.view.*

class itemAdapter(
    val context: Context,
    val itemData: List<itemList>,
    val itemSelectListener: ItemData
) :
    RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.itemName.text = itemData[position].itemName
        holder.itemView.itemSubname.text = itemData[position].itemSubName
        holder.itemView.itemPrice.text = "₹ ${itemData[position].price}"
        Picasso.get().load(itemData[position].imageUrl?.toUri()).into(holder.itemView.itemImage)

        holder.itemView.setOnClickListener {
            holder.itemView.transitionName = "container_name_$position"

            val sendData: Bundle = bundleOf(
                "transitionName" to "container_name_$position",
                "itemName" to itemData[position].itemName,
                "itemSubName" to itemData[position].itemSubName,
                "imageURL" to itemData[position].imageUrl
            )
            itemSelectListener.onItemSelectedListener(sendData)

            (context as FragmentActivity).supportFragmentManager.commit {
                setReorderingAllowed(true)
                addSharedElement(it, "container_name_$position")
                replace(R.id.main_framelayout, itemDetail())
                addToBackStack(null)
            }
        }
    }

    override fun getItemCount(): Int = itemData.size
}