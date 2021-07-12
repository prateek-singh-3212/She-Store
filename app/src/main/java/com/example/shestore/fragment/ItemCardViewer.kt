package com.example.shestore.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Adapter.ItemAdapter
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.setData
import com.example.shestore.R
import com.example.shestore.Utility.NavigationIconClickListener
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.android.synthetic.main.fragment_item_card_viewer.view.*

class ItemCardViewer : Fragment(), ItemData {

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_item_card_viewer, container, false)

        /**
         * Sets the dropdown navbar (Back Drop). We can change the type of back drop from class NavigationIconClicklListener.
         */

        setHasOptionsMenu(true)
        view.action_bar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                view.itemlist_recyclerview
            )
        )

        setViews(view)

        /** TODO('If I use 'kotlin-android-extensions' this extention then app will crash
         * because it will not be able to find out view')
         * eg. itemlist_recyclerview.adapter = context?.let { ItemAdapter(it, setData()) }
         */

        if (context != null) {
            recyclerView.adapter = context?.let { ItemAdapter(it, setData(), this) }
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        } else {
            Toast.makeText(context, "No Context Found", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    private fun setViews(view: View) {
        recyclerView = view.findViewById(R.id.itemlist_recyclerview)
        toolbar = view.findViewById(R.id.action_bar)
    }

    override fun onItemSelectedListener(bundle: Bundle) {
        setFragmentResult("itemDetails", bundle)


        exitTransition = MaterialElevationScale(false).apply {

            /**
             * This Duration Should be same as Fragment B entering transition. Because when Fragment B comes up
             * then fragments A should go down with same speed. This create the effect that Fragment A is still present
             * between transition
             */

            duration = 300L
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 300L
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * We have to wait for recycler view to recreate so we have to postpone the Transition.
         */
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        /**
         * Added On click backdrop functionality
         * TODO Add the exit animation of this backdrop
         */

        val animator = ObjectAnimator.ofFloat(
            view.findViewById(R.id.aaa),
            "translationY",
            100f
        )
        animator.duration = 500

        view.findViewById<View>(R.id.nav_backdrop_allcategories).setOnClickListener{
            (context as FragmentActivity).supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.main_framelayout, AllCategories())
                addToBackStack(null)
            }
            Toast.makeText(context, "All Categories", Toast.LENGTH_SHORT).show()
        }
    }
}