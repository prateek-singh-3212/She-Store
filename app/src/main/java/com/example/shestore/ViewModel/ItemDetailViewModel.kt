package com.example.shestore.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shestore.Database.Entity.CartEntity
import com.example.shestore.Database.Entity.WishlistEntity
import com.example.shestore.Interface.FeedbackListener
import com.example.shestore.Interface.FeedbackType
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Repository.CartWishlistRepository

class ItemDetailViewModel() : ViewModel(), FeedbackListener {

    private val itemDetail: MutableLiveData<WooCommerceItemsDetail> = MutableLiveData()
    private val transitionName: MutableLiveData<String> = MutableLiveData()
    private var cartWishlistRepo: CartWishlistRepository? = null
    private val response: MutableLiveData<Map<FeedbackType, String>> = MutableLiveData()

    init {
    }

    fun setItemDetail(data: WooCommerceItemsDetail) = itemDetail.postValue(data)

    fun getItemDetail(): MutableLiveData<WooCommerceItemsDetail> = itemDetail

    fun getTransitionName() : MutableLiveData<String> = transitionName

    fun setTransitionName(transitionName: String) = this.transitionName.postValue(transitionName)

    fun addToWishlist(context: Context, data: WishlistEntity) {
        if (cartWishlistRepo == null) {
            cartWishlistRepo = CartWishlistRepository(context, this)
        }
        cartWishlistRepo!!.addToWishlist(data)
    }

    fun checkWishlistStatus(context: Context, p_id: Int): LiveData<Int> {
        if (cartWishlistRepo == null) {
            cartWishlistRepo = CartWishlistRepository(context, this)
        }
        return cartWishlistRepo!!.isProductInWishlist(p_id)
    }

    fun addToCart(context: Context, data: CartEntity) {
        if (cartWishlistRepo == null) {
            cartWishlistRepo = CartWishlistRepository(context, this)
        }
        cartWishlistRepo!!.addToCart(data)
    }

    fun checkCartStatus(context: Context, p_id: Int): LiveData<Int> {
        if (cartWishlistRepo == null) {
            cartWishlistRepo = CartWishlistRepository(context, this)
        }
        return cartWishlistRepo!!.isProductInCart(p_id)
    }

    /** Returns every feedback from couritines */
    fun getFeedback() : MutableLiveData<Map<FeedbackType, String>> = response

    override fun message(type: FeedbackType, response: String) {
        this.response.postValue(mapOf(type to response))
    }
}