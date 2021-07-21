package com.example.shestore.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.viewpager2.widget.ViewPager2
import com.example.shestore.Adapter.ImageSliderAdapter
import com.example.shestore.R
import com.google.android.material.transition.MaterialContainerTransform
import com.ortiz.touchview.TouchImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewpager_zoom_image.*
import java.lang.Exception

class ImageViewPager : Fragment() {

    private lateinit var touchImageView: TouchImageView
    private lateinit var actiobar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 300L
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.WHITE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.viewpager_zoom_image, container, false)
        setViews(view)
        setActionbar()

        // Data comming from Image Detail
        // TODO: Extract request key to constant file
        setFragmentResultListener("itemSlideImage") { requestKey, bundle ->

            viewpager_zoom.transitionName = bundle.getString("transitionName").toString()

            Picasso.get().load(bundle.get("imageURL").toString().toUri()).placeholder(R.drawable.close_icon)
                .error(R.drawable.ic_launcher_background)
                .into(touchImageView)
        }

        return view;
    }

    private fun setActionbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(actiobar)
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setHomeAsUpIndicator(R.drawable.close_icon)
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setDisplayShowTitleEnabled(false)
    }

    private fun setViews(view: View) {
        touchImageView = view.findViewById(R.id.viewpager_touch_imageview)
        actiobar = view.findViewById(R.id.viewpager_touch_toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}