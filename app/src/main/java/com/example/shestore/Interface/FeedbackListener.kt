package com.example.shestore.Interface

/**
 * Use this to listen all the feedback from the coroutines(Repo) to viewModel.
 * It can be used for any type of listening and not for any specific use
 * */
interface FeedbackListener {

    /** Get's the message in the form of STRING*/
    fun message(response: String)
}