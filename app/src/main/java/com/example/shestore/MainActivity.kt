package com.example.shestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shestore.fragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * To Learn Material Transformation GOTO https://developersbreach.com/shared-element-transition-android/
 *
 * We have to use AndroidEntryPoint wheather we use it or not because our fragment associated with this activity is using
 * */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace<MainFragment>(R.id.main_framelayout)
        }
    }
}