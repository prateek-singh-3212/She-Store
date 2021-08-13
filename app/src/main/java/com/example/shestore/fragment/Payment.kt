package com.example.shestore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shestore.R
import com.example.shestore.Utility.DataProvider
import com.google.android.material.snackbar.Snackbar

class Payment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var paymentMethod: AutoCompleteTextView
    private lateinit var continueBtn: Button

    //Credit Card Views
    private lateinit var creditCardNumber: EditText
    private lateinit var creditCardExpiry: EditText
    private lateinit var creditCardCVV: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_payment, container, false)

        setViews(view)

        /**
         * Google material design requires the textview to show the items in list
         * That's why I created dropdown_list
         * */
        val paymentAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_list, DataProvider(requireContext()).getPaymentMethods())
        paymentMethod.setAdapter(paymentAdapter)

        paymentMethod.onItemSelectedListener

        continueBtn.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.commit{
                addToBackStack(null)
                replace<ReviewOrder>(R.id.checkout_container)
            }
        }

        return view
    }

    private fun setViews(view: View) {
        paymentMethod = view.findViewById(R.id.payment_method)
        continueBtn = view.findViewById(R.id.payment_continue)
        // Credit Card
        creditCardCVV = view.findViewById(R.id.payment_cvv)
        creditCardNumber = view.findViewById(R.id.payment_card_number)
        creditCardExpiry = view.findViewById(R.id.payment_exp_date)
    }

    // On Payment Option Item Selected
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val dataProvider = DataProvider(requireContext())

        when (dataProvider.getPaymentMethods()[position]) {
            dataProvider.getStringFromRes(R.string.paypal) -> {
                // TODO Load Paypal payment layout.
            }
            dataProvider.getStringFromRes(R.string.credit_card) -> {
                // TODO Load credit card layout
            }
            dataProvider.getStringFromRes(R.string.stripe) -> {
                // TODO Load stripe layout
            }
            dataProvider.getStringFromRes(R.string.google_pay) -> {
                // TODO Load google pay layout
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Snackbar.make(requireView(), "Select Payment Method", Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .show()
    }
}