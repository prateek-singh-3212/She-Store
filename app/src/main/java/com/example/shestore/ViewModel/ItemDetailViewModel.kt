package com.example.shestore.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shestore.Model.WooCommerceItemsDetail
import javax.inject.Inject

class ItemDetailViewModel @Inject constructor(): ViewModel() {

    private val itemDetail: MutableLiveData<WooCommerceItemsDetail> = MutableLiveData()
    private val transitionName: MutableLiveData<String> = MutableLiveData()

    fun setItemDetail(data: WooCommerceItemsDetail) = itemDetail.postValue(data)

    fun getItemDetail(): MutableLiveData<WooCommerceItemsDetail> = itemDetail

    fun getTransitionName() : MutableLiveData<String> = transitionName

    fun setTransitionName(transitionName: String) = this.transitionName.postValue(transitionName)
}