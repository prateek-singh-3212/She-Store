package com.example.shestore.Network

import android.content.Context
import android.util.Log
import com.example.shestore.Hilt.MyApplication
import com.example.shestore.Private.Keys
import com.example.shestore.Utility.SystemConstant.cacheSizeRetrofit
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

class WooCommerceAPIClient {

    private var retrofit: Retrofit? = null
    private val context: Context = MyApplication.getAppContext().applicationContext

    fun getClient(): Retrofit? {

        if (retrofit == null) {

            // Secret Keys. Validating the OAuth1.0 Request. or Attaching the keys at the end of URL
            val consumer = OkHttpOAuthConsumer(Keys.getConsumerKey(), Keys.getConsumerSecretKey())

            // Logs the request sent to server
            val logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            // Making OkHTTP Client
            val client: OkHttpClient = OkHttpClient().newBuilder()
                .cache(Cache(context.cacheDir, cacheSizeRetrofit))
                .addInterceptor(SigningInterceptor(consumer))
                .addInterceptor(logInterceptor)
                .addNetworkInterceptor(networkInterceptor()) // Network Interceptor.
                .build()

            // TODO: App Crashes when response from server is in html
            //  (Rare occurs when server redirects the request to another webpage)
            retrofit = Retrofit.Builder()
                .baseUrl("https://shestoreandroid.000webhostapp.com/wp-json/wc/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }
        return retrofit
    }

    /**
     * This interceptor will be called ONLY if the network is available
     * @return
     */
    private fun networkInterceptor() : Interceptor {
        return Interceptor {

            val response: Response = it.proceed(it.request())

            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(5, java.util.concurrent.TimeUnit.MINUTES)
                .build()

            Log.d("OkH", "networkInterceptor: Network Interseptor")

            response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }
}