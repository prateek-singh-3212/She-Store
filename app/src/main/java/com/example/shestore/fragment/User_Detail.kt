package com.example.shestore.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shestore.R

class User_Detail : Fragment() {

    private lateinit var continueBtn: Button
    private lateinit var name: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var email: EditText
    private lateinit var pincode: EditText
    private lateinit var houseNumber: EditText
    private lateinit var area: EditText
    private lateinit var landmark: EditText
    private lateinit var city: EditText
    private lateinit var state: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_user_detail, container, false)

        setViews(view)

        // TO Display error
        pincode.error = "Error"
        area.error= "Error"

        continueBtn.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.commit{
                addToBackStack(null)
                replace<Payment>(R.id.checkout_container)
            }
        }

        return view
    }

    private fun setViews(view: View) {
        continueBtn = view.findViewById(R.id.user_detail_continue)
        name = view.findViewById(R.id.user_detail_name)
        phoneNumber = view.findViewById(R.id.user_detail_phone)
        email = view.findViewById(R.id.user_detail_email)
        pincode = view.findViewById(R.id.user_detail_pincode)
        houseNumber = view.findViewById(R.id.user_detail_houseno)
        area = view.findViewById(R.id.user_detail_area)
        landmark = view.findViewById(R.id.user_detail_landmark)
        city = view.findViewById(R.id.user_detail_city)
        state = view.findViewById(R.id.user_detail_state)
    }
}