package com.example.shestore.Repository

import android.util.Log
import com.example.shestore.Interface.ItemDetailStatus
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Utility.Coroutines
import com.example.shestore.WooCommerce.ItemDetailAPI
import retrofit2.Response

class ItemDetailRepository(private val itemDetailStatusListener: ItemDetailStatus) {

    private val itemDetail: ItemDetailAPI = ItemDetailAPI()
    private val ioCoroutine = Coroutines

    fun fetchProductDetails(endpointOfData : String) {

        ioCoroutine.launchIO {
            itemDetailStatusListener.dataLoadingStatus(true)

            val detail: Response<List<WooCommerceItemsDetail>> = itemDetail.getProductDetail(endpointOfData)

            if (detail.raw().networkResponse != null){
                Log.d("OkH", "Respose from Network")
            } else if (detail.raw().cacheResponse != null && detail.raw().networkResponse == null) {
                Log.d("OkH", "Respose from Cache")
            }

            if (detail.isSuccessful ) {
                itemDetailStatusListener.onLoadComplete(detail.body()!!)
                itemDetailStatusListener.dataLoadingStatus(false)
            } else {
                itemDetailStatusListener.onErrorOccurred("Unable to Fetch Data Error Code: ${detail.code()}")
                itemDetailStatusListener.dataLoadingStatus(false)
            }
        }
    }

    fun cancelFetchRequest(cancelMessage: String) = ioCoroutine.cancelIOCoroutine(cancelMessage)
}