package com.example.shestore.Network

import com.example.shestore.Model.WooCommerceItemsDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WooCommerceAPI {

    /**
     * Use to Get All Products from the server.
     * @return It returns the Response of List of Products from server
     * */
    @GET("products")
    suspend fun getAllProductsDetail(): Response<List<WooCommerceItemsDetail>>

    /**
     * Use products?category=ID to fetch product of givenID
     *
     * @param endpointOfData Takes the URL of endpoint
     * @return It returns the Response of List of Products from server
     * */
    @GET()
    suspend fun getEndpointProductsDetail(@Url endpointOfData: String): Response<List<WooCommerceItemsDetail>>

    /**
     * Get all product from the sepcified ids in the list
     *
     * @param ids List of ids of product which we want to fetch
     * @return It returns the respose of list of requested product from server
     * */
    @GET()
    suspend fun getProductFromId(@Url endpointOfIds : String) : Response<List<WooCommerceItemsDetail>>
}