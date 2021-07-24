package com.example.shestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shestore.R
import com.example.shestore.fragment.User_Detail

class Checkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        supportFragmentManager.commit {
            replace<User_Detail>(R.id.checkout_container)
        }
    }
}