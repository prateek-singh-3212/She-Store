package com.example.shestore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.shestore.Interface.ItemDetailStatus
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Repository.CartWishlistRepository

class WishlistViewModel(application: Application) : AndroidViewModel(application), ItemDetailStatus {

    private val cartWishlistRepository: CartWishlistRepository = CartWishlistRepository(application, this)

    private var mutableProductData: MutableLiveData<List<WooCommerceItemsDetail>> = MutableLiveData()
    private var mutableDataLoadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    private var mutableOnErrorOccurred: MutableLiveData<String> = MutableLiveData()

    fun loadWishlistItems(): MutableLiveData<List<WooCommerceItemsDetail>> {
        cartWishlistRepository.fetchWishlistProductsFromServer()
        return mutableProductData
    }

    fun isDataLoading(): MutableLiveData<Boolean> {
        return mutableDataLoadingStatus
    }

    fun getError(): MutableLiveData<String> {
        return mutableOnErrorOccurred
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