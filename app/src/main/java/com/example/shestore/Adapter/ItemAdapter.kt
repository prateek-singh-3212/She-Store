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
import com.example.shestore.fragment.ItemDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_item.view.*

class ItemAdapter(
    val context: Context,
    val itemData: List<itemList>,
    val itemSelectListener: ItemData
) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.itemName.text = itemData[position].itemName
        holder.itemView.itemSubname.text = itemData[position].itemSubName
        holder.itemView.itemPrice.text = "₹ ${itemData[position].price}"
        Picasso.get().load(itemData[position].imageUrl?.toUri()).into(holder.itemView.itemImage)

        /**
         * Set the Transition name here only not in onClick Listener. If we put there then it won't work
         * because when we press back it will not assign the transition name to the card as it is in setOnClickListener.
         * */
        holder.itemView.cardview_item.transitionName = "container_name_$position"

        holder.itemView.setOnClickListener {

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
                replace(R.id.main_framelayout, ItemDetail())
                addToBackStack(null)
            }
        }
    }

    override fun getItemCount(): Int = itemData.size
}