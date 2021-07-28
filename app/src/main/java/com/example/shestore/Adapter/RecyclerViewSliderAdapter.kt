package com.example.shestore.Adapter

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shestore.Interface.ItemData
import com.example.shestore.Model.TabModel
import com.example.shestore.R
import com.example.shestore.ViewModel.ItemDetailViewModel
import dagger.hilt.android.internal.managers.ViewComponentManager
import kotlinx.android.synthetic.main.viewpager_itemlist_rv.view.*

class RecyclerViewSliderAdapter(val itemDetailViewModel: ItemDetailViewModel, val categoryList: List<TabModel>, val context: Activity, val itemSelected: ItemData) : RecyclerView.Adapter<RecyclerViewSliderAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewpager_itemlist_rv, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        /**
//         * WORK FLOW
//         * It Creates the new viewModel for each page of tab layout.
//         * TODO: Apply the caching strategy on this. Because every time TabLayout's RV Recreates the it generate the new get request.
//         *
//         * the KEY which is used to differentiate between the ViewModels that needs to be fetched. So by using the positions in the
//         * ViewPager as a unique key, you should be able to have a unique ViewModel for each viewpager_itemlist_rv
//         * */
//        val itemDetailViewModel: ItemDetailViewModel = ViewModelProvider(context as AppCompatActivity).get( position.toString(),ItemDetailViewModel::class.java)

        /**
         * This will observe and get's the data from its respective view model.
         * IT WILL TAKE THE ENDPOINT DATA WHICH TELLS THAT WHICH TYPE OF PRODUCTS YOU WANT TO FETCH FROM SERVER.
         * */
        Log.d("OkHttpClient: --> GET",  categoryList[position].catName)
        itemDetailViewModel.getItemDetail(categoryList[position].categoryEndpointURL).observeForever {

            holder.itemView.viewpager_itemlist_rv.adapter = ItemAdapter(context, it, itemSelected)
            holder.itemView.viewpager_itemlist_rv.layoutManager = GridLayoutManager(context, 2)
        }

        itemDetailViewModel.isDataLoading().observeForever {
            if (it)
                Log.d("OkHttp", "DATA IS LOADING..............")
            else
                Log.d("OkHttp", "DATA LOAD COMPLETE!!!!!!!!")
        }

        itemDetailViewModel.getError().observeForever {
            Log.d("OkHttp", "STOP!!!!!!!!!!! Error Occurred $it")
        }
    }

    override fun getItemCount(): Int = categoryList.size
}