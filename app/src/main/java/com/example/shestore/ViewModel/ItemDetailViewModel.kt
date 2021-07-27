package com.example.shestore.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shestore.Interface.ItemDetailStatus
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Repository.ItemDetailRepository

class ItemDetailViewModel : ViewModel(), ItemDetailStatus {

    private var itemDetailRepo: ItemDetailRepository = ItemDetailRepository(this)
    private var mutableProductData : MutableLiveData<List<WooCommerceItemsDetail>> = MutableLiveData()
    private var mutableDataLoadingStatus : MutableLiveData<Boolean> = MutableLiveData()
    private var mutableOnErrorOccurred : MutableLiveData<String> = MutableLiveData()


    fun getItemDetail(endpointOfData :String) : MutableLiveData<List<WooCommerceItemsDetail>> {
        itemDetailRepo.fetchProductDetails(endpointOfData)
        return mutableProductData
    }

    fun isDataLoading() : MutableLiveData<Boolean> {
        return mutableDataLoadingStatus
    }

    fun getError() : MutableLiveData<String> {
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