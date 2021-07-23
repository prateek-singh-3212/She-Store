package com.example.shestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shestore.fragment.ItemCardViewer

/**
 * To Learn Material Transformation GOTO https://developersbreach.com/shared-element-transition-android/
 * */

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace<ItemCardViewer>(R.id.main_framelayout)
        }
    }
}