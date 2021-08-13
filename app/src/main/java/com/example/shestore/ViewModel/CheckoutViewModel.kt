package com.example.shestore.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.shestore.Model.BillingDataModel
import com.example.shestore.Model.ShippingDataModel
import com.example.shestore.Repository.CartWishlistRepository

class CheckoutViewModel(application: Application)  : AndroidViewModel(application){

    private val cartWishlistRepository = CartWishlistRepository(application)
    private val billingAddress: MutableLiveData<BillingDataModel> = MutableLiveData()
    private val shippingAddress: MutableLiveData<ShippingDataModel> = MutableLiveData()
    private var paymentMethodId: String? = null
    private var paymentMethodTitle: String? = null

    /** Sets the billing address in Live Data*/
    fun setBillingAddress(billingAddress: BillingDataModel) = this.billingAddress.postValue(billingAddress)

    /** Get the billing address in Live Data*/
    fun getBillingAddress() : MutableLiveData<BillingDataModel> = this.billingAddress

    /** Sets the billing address in Live Data*/
    fun setShippingAddress(shippingAddress: ShippingDataModel) = this.shippingAddress.postValue(shippingAddress)

    /** Get the billing address in Live Data*/
    fun getShippingAddress() : MutableLiveData<ShippingDataModel> = this.shippingAddress

    /** Set the payment method data*/
    fun setPaymentData(paymentMethodId: String, paymentMethodTitle: String) {
        this.paymentMethodId = paymentMethodId
        this.paymentMethodTitle = paymentMethodTitle
        // Set Paid is false. It will be true when order is placed...
    }
}