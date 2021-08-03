package com.example.shestore.ViewModel

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shestore.Database.Entity.CartEntity
import com.example.shestore.Interface.FeedbackListener
import com.example.shestore.Interface.FeedbackType
import com.example.shestore.Interface.ItemDetailStatus
import com.example.shestore.Model.QuantitySizeModel
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Repository.CartWishlistRepository

class CartViewModel(application: Application) : AndroidViewModel(application), FeedbackListener, ItemDetailStatus{

    private var cartWishlistRepository: CartWishlistRepository =
        CartWishlistRepository(application, this, this)

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
//     val dataMap = HashMap<Int, QuantitySizeModel>()
//        cartWishlistRepository.getCartProducts().observe(activity) {
//            for (item in it) {
//                dataMap.put(item.product_id, QuantitySizeModel(item.size, item.quantity))
//            }
//        }
        return cartWishlistRepository.getCartProducts()
    }


    override fun message(type: FeedbackType, response: String) {
        // IMPLEMENT
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