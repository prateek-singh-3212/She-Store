package com.example.shestore.Utility

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object Coroutines {

    fun <T> launchMain(task: suspend CoroutineScope.() -> T): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            task()
        }
    }

    fun <T> launchIO(task: suspend CoroutineScope.() -> T): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            task()
        }
    }
}