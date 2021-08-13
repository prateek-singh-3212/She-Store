package com.example.shestore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.shestore.Model.BillingDataModel
import com.example.shestore.Model.ShippingDataModel
import com.example.shestore.R
import com.example.shestore.ViewModel.CheckoutViewModel

class User_Detail : Fragment() {

    private lateinit var checkoutViewModel: CheckoutViewModel

    private lateinit var continueBtn: Button
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var email: EditText
    private lateinit var pincode: EditText
    private lateinit var address1: EditText
    private lateinit var address2: EditText
    private lateinit var country: EditText
    private lateinit var city: EditText
    private lateinit var state: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_user_detail, container, false)

        checkoutViewModel = ViewModelProvider(this).get(CheckoutViewModel::class.java)

        setViews(view)

        continueBtn.setOnClickListener {
            checkBillingFields()
        }


        return view
    }

    private fun checkBillingFields() {
        when {
            firstName.text.isEmpty() -> {
                firstName.error = "First Name is REQUIRED"
            }
            lastName.text.isEmpty() -> {
                lastName.error = "Last Name is REQUIRED"
            }
            address1.text.isEmpty() -> {
                address1.error = "Address 1 is REQUIRED"
            }
            address2.text.isEmpty() -> {
                address2.error = "Address 2 is REQUIRED"
            }
            city.text.isEmpty() -> {
                city.error = "City is REQUIRED"
            }
            state.text.isEmpty() -> {
                state.error = "State is REQUIRED"
            }
            pincode.text.isEmpty() -> {
                pincode.error = "Pin Code is REQUIRED"
            }
            country.text.isEmpty() -> {
                country.error = "Country is REQUIRED"
            }
            email.text.isEmpty() -> {
                email.error = "Email is REQUIRED"
            }
            phoneNumber.text.isEmpty() -> {
                phoneNumber.error = "Phone Number is REQUIRED"
            }
            else -> {
                // TODO: Add more feilds and checkbox to set seprate shipping address.
                setShippingData()

                setBillingData()

                // Next Fragment
                (context as AppCompatActivity).supportFragmentManager.commit{
                    addToBackStack(null)
                    replace<Payment>(R.id.checkout_container)
                }
            }
        }
    }

    fun setBillingData() {
        checkoutViewModel.setBillingAddress(BillingDataModel(
            firstName.text.toString(),
            lastName.text.toString(),
            address1.text.toString(),
            address2.text.toString(),
            city.text.toString(),
            state.text.toString(),
            pincode.text.toString().toInt(),
            country.text.toString(),
            email.text.toString(),
            phoneNumber.text.toString(),
        ))
    }

    fun setShippingData() {
        // TODO Change this in future with seprate feilds..
        checkoutViewModel.setShippingAddress(ShippingDataModel(
            firstName.text.toString(),
            lastName.text.toString(),
            address1.text.toString(),
            address2.text.toString(),
            city.text.toString(),
            state.text.toString(),
            pincode.text.toString().toInt(),
            country.text.toString(),
        ))
    }

    private fun setViews(view: View) {
        continueBtn = view.findViewById(R.id.user_detail_continue)
        firstName = view.findViewById(R.id.user_detail_first_name)
        lastName = view.findViewById(R.id.user_detail_last_name)
        phoneNumber = view.findViewById(R.id.user_detail_phone)
        email = view.findViewById(R.id.user_detail_email)
        pincode = view.findViewById(R.id.user_detail_pincode)
        address1 = view.findViewById(R.id.user_detail_houseno)
        address2 = view.findViewById(R.id.user_detail_area)
        country = view.findViewById(R.id.user_detail_country)
        city = view.findViewById(R.id.user_detail_city)
        state = view.findViewById(R.id.user_detail_state)
    }
}