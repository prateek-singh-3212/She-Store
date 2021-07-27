package com.example.shestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.shestore.fragment.ItemCardViewer
import kotlin.system.measureTimeMillis

/**
 * To Learn Material Transformation GOTO https://developersbreach.com/shared-element-transition-android/
 * */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val time = measureTimeMillis {
//
//            Log.d("OkHttpClient", "onCreate1: ${Thread.currentThread().name}")
//            GlobalScope.launch {
//                delay(10000)
//                Log.d("OkHttpClient", "onCreate2: ${Thread.currentThread().name}")
//                ItemDetailAPI().getAllProductsDetail()
//            }
//
//            Log.d("OkHttpClient", "onCreate2: ${Thread.currentThread().name}")

            supportFragmentManager.commit {
                replace<ItemCardViewer>(R.id.main_framelayout)
            }
        }
//        Log.d("OkHttpClient", "onCreate1: ${time}")

    }
}