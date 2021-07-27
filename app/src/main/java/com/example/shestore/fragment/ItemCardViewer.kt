package com.example.shestore.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.viewpager2.widget.ViewPager2
import com.example.shestore.Adapter.RecyclerViewSliderAdapter
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.setTabData
import com.example.shestore.R
import com.example.shestore.Utility.NavigationIconClickListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.android.synthetic.main.fragment_item_card_viewer.view.*

class ItemCardViewer : Fragment(), ItemData {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewpager: ViewPager2

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

        // Sets the viewpager to view the data in tabs. on home screen.
        viewpager.adapter =
            RecyclerViewSliderAdapter(setTabData(), requireContext(), this)

        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            val tabData = setTabData()
            tab.text = tabData[position].catName
            tab.setIcon(tabData[position].catImageResId)
        }.attach()

        return view
    }

    private fun setActionBar(view: View) {
        /**
         * Sets the dropdown navbar (Back Drop). We can change the type of back drop from class NavigationIconClicklListener.
         */
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = ""

        view.action_bar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                view.itemList_constraintLayout
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar_item_card_viewer, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
//        recyclerView = view.findViewById(R.id.itemlist_recyclerview)
        toolbar = view.findViewById(R.id.action_bar)
        tabLayout = view.findViewById(R.id.itemList_tab)
        viewpager = view.findViewById(R.id.itemList_viewpager)
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
         */
        view.findViewById<View>(R.id.nav_backdrop_allcategories).setOnClickListener {
            (context as FragmentActivity).supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.main_framelayout, AllCategories())
                addToBackStack(null)
            }
        }
    }
}