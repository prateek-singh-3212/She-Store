package com.example.shestore.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Adapter.ItemAdapter
import com.example.shestore.R
import com.example.shestore.ViewModel.ItemCardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardViewerFragment(private val categoryEndpointURL: String) : Fragment() {

    private val itemCardVM: ItemCardViewModel by viewModels()
    private lateinit var lottie_loading: LinearLayout
    private lateinit var lottie_error: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_card_viewer, container, false)
        val rv: RecyclerView = view.findViewById(R.id.home_itemlist_rv)
        lottie_loading = view.findViewById(R.id.card_viewer_loading)
        lottie_error = view.findViewById(R.id.card_viewer_error)

        itemCardVM.getItemDetail(categoryEndpointURL).observeForever {

            rv.adapter = ItemAdapter(requireActivity(), it)
            rv.layoutManager = GridLayoutManager(context, 2)
        }

        itemCardVM.isDataLoading().observeForever {
            if (it) {
                rv.visibility = View.INVISIBLE
                lottie_loading.visibility = View.VISIBLE
            } else {
                rv.visibility = View.VISIBLE
                lottie_loading.visibility = View.INVISIBLE
            }
        }

        itemCardVM.getError().observeForever {
            rv.visibility = View.INVISIBLE
            lottie_error.visibility = View.VISIBLE
            val error: TextView = lottie_error.findViewById(R.id.error_message)
            error.text = it
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LALA", "CardViewer Fragment Destroyed")
        itemCardVM.cancelFetchRequest("CardViewer Fragment Destroyed")
    }
}