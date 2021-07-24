package com.example.shestore.Utility

import android.content.Context
import androidx.annotation.StringRes
import com.example.shestore.R

/**
 * Provide all the constant or data which changes most frequently to this class.
 * */

class DataProvider(val context: Context) {
    fun getPaymentMethods() : List<String> = listOf(getStringFromRes(R.string.credit_card),
        getStringFromRes(R.string.paypal), getStringFromRes(R.string.stripe), getStringFromRes(R.string.google_pay))

    fun getStringFromRes(@StringRes stringId: Int): String = context.getString(stringId)
}