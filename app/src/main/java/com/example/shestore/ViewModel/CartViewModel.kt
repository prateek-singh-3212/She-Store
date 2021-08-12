package com.example.shestore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shestore.Database.Entity.CartEntity
import com.example.shestore.Interface.ItemDetailStatus
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Repository.CartWishlistRepository

class CartViewModel(application: Application) : AndroidViewModel(application), ItemDetailStatus{

    private var cartWishlistRepository: CartWishlistRepository =
        CartWishlistRepository(application, this)

    private var mutableProductData: MutableLiveData<List<WooCommerceItemsDetail>> =
        MutableLiveData()
    private var mutableDataLoadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    private var mutableOnErrorOccurred: MutableLiveData<String> = MutableLiveData()


    fun loadCartItems(): MutableLiveData<List<WooCommerceItemsDetail>> {
        cartWishlistRepository.fetchCartProductsFromServer()
        return mutableProductData
    }

    fun isDataLoading(): MutableLiveData<Boolean> {
        return mutableDataLoadingStatus
    }

    fun getError(): MutableLiveData<String> {
        return mutableOnErrorOccurred
    }

    fun getSizeAndQuantity() : LiveData<List<CartEntity>> {
        return cartWishlistRepository.getCartProducts()
    }

    override fun dataLoadingStatus(isDataLoading: Boolean) {
        mutableDataLoadingStatus.postValue(isDataLoading)
    }

    override fun onLoadComplete(itemData: List<WooCommerceItemsDetail>) {
        mutableProductData.postValue(itemData)
    }

    override fun onErrorOccurred(error: String) {
        mutableOnErrorOccurred.postValue(error)
    }
}