package com.example.shestore.Repository

import com.example.shestore.Interface.ItemDetailStatus
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Utility.Coroutines
import com.example.shestore.WooCommerce.ItemDetailAPI
import retrofit2.Response

class ItemDetailRepository(private val itemDetailStatusListener: ItemDetailStatus) {

    private val itemDetail: ItemDetailAPI = ItemDetailAPI()

    fun fetchProductDetails(endpointOfData : String) {

        Coroutines.launchIO {
            itemDetailStatusListener.dataLoadingStatus(true)

            val detail: Response<List<WooCommerceItemsDetail>> = itemDetail.getProductDetail(endpointOfData)

            if (detail.isSuccessful ) {
                itemDetailStatusListener.onLoadComplete(detail.body()!!)
                itemDetailStatusListener.dataLoadingStatus(false)
            } else {
                itemDetailStatusListener.onErrorOccurred("Unable to Fetch Data Error Code: ${detail.code()}")
                itemDetailStatusListener.dataLoadingStatus(false)
            }
        }
    }
}