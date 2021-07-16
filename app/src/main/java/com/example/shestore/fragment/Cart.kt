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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.shestore.Adapter.CartAdapter
import com.example.shestore.Model.setData
import com.example.shestore.R

class Cart : Fragment() {

    private lateinit  var cartrv : RecyclerView
    private lateinit var cartToolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.add_cart)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_cart, container, false)

        setViews(view)
        setActionbar()
        // Set's Recyclerview
        cartrv.adapter = CartAdapter(setData())
        cartrv.layoutManager = LinearLayoutManager(context)

        return view
    }

    private fun setViews(view: View) {
        cartrv = view.findViewById(R.id.cart_rv)
        cartToolbar = view.findViewById(R.id.cart_toolbar)
    }

    private fun setActionbar() {
        (context as AppCompatActivity).setSupportActionBar(cartToolbar)
        val actionBar : ActionBar? = (context as AppCompatActivity).supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.close_icon)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = ""
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}