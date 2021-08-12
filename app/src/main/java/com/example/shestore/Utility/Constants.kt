package com.example.shestore.Utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Constants {
    const val TRANSITION_TIME = 150L
    const val KEY_ITEM_SLIDE_IMAGE_FRAGMENT_DATA= "itemSlideImage"
    const val ATTRIBUTE_SIZE = "Size"

//  TODO: Add Function to check network connection
}

object SystemConstant {
    /**
     * It is cache size of retrofit. Maximum cache stored by retrofit.
     * THIS IS 5MB
     * */
    const val cacheSizeRetrofit : Long = (5 * 1024 * 1024).toLong()

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}

object SystemErrors {
    const val notFound404 : String = "SHE404 : No data found"
    const val statusOK200 : String = "SHE200 : Status OK"
}