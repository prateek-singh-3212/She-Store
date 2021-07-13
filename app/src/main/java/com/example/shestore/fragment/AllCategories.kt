package com.example.shestore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.shestore.Adapter.CategoryAdapter
import com.example.shestore.Model.setCategoryData
import com.example.shestore.R

class AllCategories : Fragment() {

    private lateinit var toolbar : Toolbar
    private lateinit var categoryRV : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Sets the actionbar
        setHasOptionsMenu(true)

        /**
         * Sets the enter transition for the fragment. Transition used ( nav_to_fragment.xml)
         * */
        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.nav_to_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_all_categories, container, false)

        setViews(view)
        setActionBar()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryRV.adapter = CategoryAdapter(setCategoryData())
        categoryRV.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setViews(view: View) {
        toolbar = view.findViewById(R.id.all_categories_actionbar)
        categoryRV = view.findViewById(R.id.all_categories_rv)
    }

    private fun setActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar

        // Set the property on back button of action bar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.close_icon)

        // Hides the app name or title in actionbar
        actionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                // Back to previous fragment
                activity?.onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}