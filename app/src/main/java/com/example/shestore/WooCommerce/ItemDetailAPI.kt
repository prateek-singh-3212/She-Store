package com.example.shestore.WooCommerce

import android.util.Log
import com.example.shestore.Model.WooCommerceItemsDetail
import com.example.shestore.Network.WooCommerceAPI
import com.example.shestore.Network.WooCommerceAPIClient
import retrofit2.Response

class ItemDetailAPI {

    // Use !! because retrofit client is never be null because we are checking the null on Retrofit
    private var wooCommerceAPI: WooCommerceAPI = WooCommerceAPIClient().getClient()!!.create(
        WooCommerceAPI::class.java
    )

    /**
     * @return the Response from the Server and will check response in the repository
     * */
    suspend fun getAllProductsDetail(): Response<List<WooCommerceItemsDetail>> {
        return wooCommerceAPI.getAllProductsDetail()
    }

    suspend fun getProductDetail(endpointOfData : String) : Response<List<WooCommerceItemsDetail>> {
        return wooCommerceAPI.getEndpointProductsDetail(endpointOfData)
    }

    suspend fun getProductDetailFromIds(ids: List<Int>) : Response<List<WooCommerceItemsDetail>> {
        var endpoint = "products?include="
        for (id in ids) {
            endpoint = "$endpoint $id,"
        }
        return wooCommerceAPI.getProductFromId(endpoint)
    }
}