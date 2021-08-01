package com.example.shestore.Utility

import android.util.Log
import kotlinx.coroutines.*

object Coroutines {

    private val mainCoroutineScope = CoroutineScope(Dispatchers.Main)
    private val ioCoroutineScope = CoroutineScope(Dispatchers.IO)
    private val uiCoroutineScope = MainScope() // Perform Operation on UI from this.
    private val defaultCoroutineScope = CoroutineScope(Dispatchers.Default)

    fun <T> launchMain(task: suspend CoroutineScope.() -> T): Job {
        return mainCoroutineScope.launch {
            task()
        }
    }

    fun <T> launchIO(task: suspend CoroutineScope.() -> T): Job {
        return ioCoroutineScope.launch {
            task()
        }
    }

    fun <T> launchUICoroutine(task: suspend CoroutineScope.() -> T): Job {
        return uiCoroutineScope.launch {
            task()
        }
    }

    fun <T> launchDefault(task: suspend CoroutineScope.() -> T): Job {
        return defaultCoroutineScope.launch {
            task()
        }
    }


    fun cancelIOCoroutine(message: String) {
        Log.d("LALA", "cancelIOCoroutine: $message")
        ioCoroutineScope.cancel(message)
    }

    fun cancelMainCoroutine(message: String) {
        mainCoroutineScope.cancel(message)
    }

    fun cancelUICoroutine(message: String) = uiCoroutineScope.cancel(message)
}