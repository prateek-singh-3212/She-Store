package com.example.shestore.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.shestore.R
import com.example.shestore.Utility.Constants
import com.google.android.material.transition.MaterialContainerTransform
import com.ortiz.touchview.TouchImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewpager_zoom_image.*

class ImageViewPager : Fragment() {

    private lateinit var touchImageView: TouchImageView
    private lateinit var actionbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = Constants.TRANSITION_TIME
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

        // Data coming from Image Detail
        setFragmentResultListener("itemSlideImage") { requestKey, bundle ->

            if (requestKey != Constants.KEY_ITEM_SLIDE_IMAGE_FRAGMENT_DATA) {
                // If Key is not equal then returning back
                requireActivity().onBackPressed()
            }

            viewpager_zoom.transitionName = bundle.getString("transitionName").toString()

            Picasso.get().load(bundle.get("imageURL").toString().toUri()).placeholder(R.drawable.close_icon)
                .error(R.drawable.ic_launcher_background)
                .into(touchImageView)
        }

        return view
    }

    private fun setActionbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(actionbar)
        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setHomeAsUpIndicator(R.drawable.close_icon)
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setDisplayShowTitleEnabled(false)
    }

    private fun setViews(view: View) {
        touchImageView = view.findViewById(R.id.viewpager_touch_imageview)
        actionbar = view.findViewById(R.id.viewpager_touch_toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}