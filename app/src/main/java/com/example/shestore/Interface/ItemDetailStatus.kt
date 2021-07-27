package com.example.shestore.Interface

import com.example.shestore.Model.WooCommerceItemsDetail

interface ItemDetailStatus {

    /** On Data is Loading */
    fun dataLoadingStatus(isDataLoading : Boolean)

    /** Get the data after load finishes */
    fun onLoadComplete( itemData : List<WooCommerceItemsDetail>)

    /** On Error Occurred while parsing data */
    fun onErrorOccurred(error : String)
}