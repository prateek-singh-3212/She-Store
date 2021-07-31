package com.example.shestore.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.R
import com.example.shestore.Utility.HtmlParser
import com.example.shestore.ViewModel.ItemDetailViewModel
import com.example.shestore.fragment.ItemDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_item.view.*

class ItemAdapter(
    private val fragmentActivity: FragmentActivity,
    private val itemData: List<WooCommerceItemsDetail>
) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.itemName.text = itemData[position].name
        holder.itemView.itemSubname.text = HtmlParser.htmlToSpannedString(itemData[position].short_description).toString()
        holder.itemView.itemPrice.text = String.format("$%s", itemData[position].price)
        holder.itemView.itemRateing.text = String.format("%s ★", itemData[position].average_rating)
        Picasso.get().load(itemData[position].images[0].src.toUri()).into(holder.itemView.itemImage)

        /**
         * Set the Transition name here only not in onClick Listener. If we put there then it won't work
         * because when we press back it will not assign the transition name to the card as it is in setOnClickListener.
         * */
        holder.itemView.cardview_item.transitionName = "container_name_$position"

        holder.itemView.setOnClickListener {

            val itemDetailVM : ItemDetailViewModel = ViewModelProvider(fragmentActivity).get(ItemDetailViewModel::class.java)
            Log.d("FragmentTransition", "Fragment: $fragmentActivity ItemDataViewModel: $itemDetailVM")
            itemDetailVM.setItemDetail(itemData[position])
            itemDetailVM.setTransitionName(holder.itemView.cardview_item.transitionName)

            fragmentActivity.supportFragmentManager.commit {
                setReorderingAllowed(true)
                addSharedElement(it, holder.itemView.cardview_item.transitionName)
                replace(R.id.main_framelayout, ItemDetail())
                addToBackStack(null)
            }
        }
    }

    override fun getItemCount(): Int = itemData.size
}