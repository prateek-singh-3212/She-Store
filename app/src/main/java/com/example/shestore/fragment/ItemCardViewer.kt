package com.example.shestore.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Adapter.ItemAdapter
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.setData
import com.example.shestore.R
import com.example.shestore.Utility.ItemSwipGesture
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

        setViews(view)

        setActionBar(view)


        /** TODO('If I use 'kotlin-android-extensions' this extention then app will crash
         * because it will not be able to find out view')
         * eg. itemlist_recyclerview.adapter = context?.let { ItemAdapter(it, setData()) }
         */

        if (context != null) {
            recyclerView.adapter = context?.let { ItemAdapter(it, setData(), this) }
            recyclerView.layoutManager = GridLayoutManager(context, 2)

            // Item Swipe Gesture to Right to add item to wishlist.
            ItemTouchHelper(ItemSwipGesture(requireContext(), 0, ItemTouchHelper.RIGHT)).attachToRecyclerView(recyclerView)
        } else {
            Toast.makeText(context, "No Context Found", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    private fun setActionBar(view : View) {
        /**
         * Sets the dropdown navbar (Back Drop). We can change the type of back drop from class NavigationIconClicklListener.
         */
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = ""

        view.action_bar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                view.itemlist_recyclerview
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_item_card_viewer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionbar_item_card_viewer_mycart -> {
                (context as FragmentActivity).supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.main_framelayout, Cart())
                    addToBackStack(null)
                }
            }
        }

        return super.onOptionsItemSelected(item)
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
        view.findViewById<View>(R.id.nav_backdrop_allcategories).setOnClickListener{
            (context as FragmentActivity).supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.main_framelayout, AllCategories())
                addToBackStack(null)
            }
        }
    }
}