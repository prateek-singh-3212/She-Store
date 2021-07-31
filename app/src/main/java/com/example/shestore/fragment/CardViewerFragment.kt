package com.example.shestore.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_card_viewer, container, false)
        val rv : RecyclerView = view.findViewById(R.id.home_itemlist_rv)

        itemCardVM.getItemDetail(categoryEndpointURL).observeForever {

            rv.adapter = ItemAdapter(requireActivity(), it)
            rv.layoutManager = GridLayoutManager(context, 2)
        }

        itemCardVM.isDataLoading().observeForever {
            if (it)
                Log.d("OkHttp", "DATA IS LOADING..............")
            else
                Log.d("OkHttp", "DATA LOAD COMPLETE!!!!!!!!")
        }

        itemCardVM.getError().observeForever {
            Log.d("OkHttp", "STOP!!!!!!!!!!! Error Occurred $it")
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LALA", "CardViewer Fragment Destroyed")
        itemCardVM.cancelFetchRequest("CardViewer Fragment Destroyed")
    }
}