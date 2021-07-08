package com.example.shestore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Adapter.itemAdapter
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.setData
import com.example.shestore.R
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale

class itemCardViewer : Fragment(), ItemData {

    private val TAG = "itemCardViewer"
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_item_card_viewer, container, false)

        recyclerView = view.findViewById(R.id.itemlist_recyclerview)

        // TODO('If I use 'kotlin-android-extensions' this extention then app will crash
        //  because it will not be able to find out view')
        // eg. itemlist_recyclerview.adapter = context?.let { itemAdapter(it, setData()) }
        if (context != null) {
            recyclerView.adapter = context?.let { itemAdapter(it, setData(), this) }
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        } else {
            Toast.makeText(context, "A", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun onItemSelectedListener(bundle: Bundle) {
        setFragmentResult("itemDetails", bundle)


        exitTransition = MaterialElevationScale(false).apply {

            /** This Duration Should be same as Fragment B entering transition. Because when Fragment B comes up
            then fragments A should go down with same speed. This create the effect that Fragment A is still present
            between transition */

            duration = 300L
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 300L
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }
}