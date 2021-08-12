package com.example.shestore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.shestore.Adapter.ItemAdapter
import com.example.shestore.R
import com.example.shestore.Utility.SystemErrors
import com.example.shestore.ViewModel.WishlistViewModel

class WishlistFragment : Fragment() {

    private lateinit var wishlistToolbar: Toolbar
    private lateinit var wishlistRV: RecyclerView
    private lateinit var wishlistVm: WishlistViewModel
    private lateinit var lottie_loading: LinearLayout
    private lateinit var lottie_error: LinearLayout
    private lateinit var lottie_not_found: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        /**
         * Sets the enter transition for the fragment. Transition used ( nav_to_fragment.xml)
         * */
        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.nav_to_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_wishlist, container, false)

        wishlistVm = ViewModelProvider(this).get(WishlistViewModel::class.java)

        setViews(view)

        setActionbar()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * We have to wait for recycler view to recreate so we have to postpone the Transition.
         */
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onStart() {
        super.onStart()
        wishlistVm.loadWishlistItems().observe(this) {
            wishlistRV.adapter = ItemAdapter(requireActivity(), it)
            wishlistRV.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        wishlistVm.isDataLoading().observe(this) {
            if (it) {
                wishlistRV.visibility = View.INVISIBLE
                lottie_loading.visibility = View.VISIBLE
            } else {
                wishlistRV.visibility = View.VISIBLE
                lottie_loading.visibility = View.INVISIBLE
            }
        }

        wishlistVm.getError().observe(this){

            if (it.equals(SystemErrors.notFound404)) {
                // Not found the item in database
                lottie_not_found.visibility = View.VISIBLE
                return@observe
            } else if (it.equals(SystemErrors.statusOK200) || lottie_not_found.visibility == View.VISIBLE) {
                lottie_not_found.visibility = View.INVISIBLE
                return@observe
            }

            wishlistRV.visibility = View.INVISIBLE
            lottie_error.visibility = View.VISIBLE
            val error: TextView = lottie_error.findViewById(R.id.error_message)
            error.text = it
        }
    }

    private fun setActionbar() {
        (context as AppCompatActivity).setSupportActionBar(wishlistToolbar)
        val actionBar : ActionBar? = (context as AppCompatActivity).supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.close_icon)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = ""
    }

    private fun setViews(view: View) {
        wishlistToolbar = view.findViewById(R.id.wishlist_toolbar)
        wishlistRV = view.findViewById(R.id.wishlist_rv)
        lottie_loading = view.findViewById(R.id.wishlist_loading)
        lottie_error = view.findViewById(R.id.wishlist_error)
        lottie_not_found = view.findViewById(R.id.wishlist_not_found)
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