package com.example.shestore.Network

import com.example.shestore.Private.Keys
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class WooCommerceAPIClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {

        if (retrofit == null) {

            // Secret Keys. Validating the OAuth1.0 Request. or Attaching the keys at the end of URL
            val consumer = OkHttpOAuthConsumer(Keys.getConsumerKey(), Keys.getConsumerSecretKey())

            // Logs the request sent to server
            val logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            // Making OkHTTP Client
            val client: OkHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(SigningInterceptor(consumer))
//                .addInterceptor(logInterceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl("https://shestoreandroid.000webhostapp.com/wp-json/wc/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }
        return retrofit
    }
}