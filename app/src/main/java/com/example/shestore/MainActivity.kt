package com.example.shestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shestore.fragment.itemCardViewer

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace<itemCardViewer>(R.id.main_framelayout)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }
}