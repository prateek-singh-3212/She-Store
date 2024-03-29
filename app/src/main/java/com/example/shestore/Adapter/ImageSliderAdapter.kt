package com.example.shestore.Adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Interface.ItemData
import com.example.shestore.R
import com.example.shestore.fragment.ImageViewPager
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * This class is for view pager. It adds the image to side show view pager in item Detail
 * TODO: Add Transition name in Constants file
 * */
class ImageSliderAdapter(
    private val fragmentActivity: FragmentActivity, private val imageURL: List<String>,
    val onItemSelectedListener : ItemData) :
    RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(fragmentActivity).inflate(R.layout.viewpager_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view: ImageView = holder.itemView.findViewById(R.id.viewpager_imageview)

        holder.itemView.transitionName = "slide_image_$position"

        Picasso.get().load(imageURL[position].toUri()).placeholder(R.drawable.close_icon)
            .error(R.drawable.ic_launcher_background)
            .into(view, object : Callback {
                override fun onSuccess() {
                    Log.d("IMG", " $position ")
                }

                override fun onError(e: Exception?) {
                    Log.e("IMG", "onError: $position " + e?.message)
                }
            })

        holder.itemView.setOnClickListener {

            /**
             * Transferring the data to ImageViewPager Fragment to view Image.
             * Used Material Transition in it.
             *  */

            val sendData: Bundle = bundleOf(
                "transitionName" to "slide_image_$position",
                "imageURL" to imageURL[position]
            )

            onItemSelectedListener.onItemSelectedListener(sendData)

            fragmentActivity.supportFragmentManager.commit {
                addToBackStack(null)
                addSharedElement(it, "slide_image_$position")
                replace(R.id.main_framelayout, ImageViewPager())
            }
        }
    }

    override fun getItemCount(): Int = imageURL.size
}