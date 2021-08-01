package com.example.shestore.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shestore.Database.Entity.CartWishlistEntity
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Repository.CartWishlistRepository
import javax.inject.Inject

class ItemDetailViewModel() : ViewModel() {

    private val itemDetail: MutableLiveData<WooCommerceItemsDetail> = MutableLiveData()
    private val transitionName: MutableLiveData<String> = MutableLiveData()
    private var cartWishlistRepo: CartWishlistRepository? = null

    init {
    }

    fun setItemDetail(data: WooCommerceItemsDetail) = itemDetail.postValue(data)

    fun getItemDetail(): MutableLiveData<WooCommerceItemsDetail> = itemDetail

    fun getTransitionName() : MutableLiveData<String> = transitionName

    fun setTransitionName(transitionName: String) = this.transitionName.postValue(transitionName)

    fun addToWishlist(context: Context, data: CartWishlistEntity) {
        cartWishlistRepo = CartWishlistRepository(context)
        cartWishlistRepo!!.insetDataInDatabase(data)
    }
}