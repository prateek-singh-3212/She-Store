package com.example.shestore.Utility

import android.view.View

abstract class DoubleClickView : View.OnClickListener {

    private var lastClickTime: Long = 0

    override fun onClick(v: View?) {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME) {
            onDoubleClickListener(v)
        }
        lastClickTime = clickTime
    }

    abstract fun onDoubleClickListener(v: View?)

    companion object {
        private const val DOUBLE_CLICK_TIME : Long = 300
    }
}