package com.agung.android.kotlinapi.data.api

import android.os.Build
import com.agung.android.kotlinapi.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit

/**
 * Created by agung on 07/02/18.
 */
object ApiClient {
    const val BASE_URL = "https://api.github.com/"
    const val GIT_HUB_USER = "google"

    @JvmStatic
    fun gitHubService(baseURL: String): ApiService{
        val httpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)

        if (BuildConfig.DEBUG){
            val loging = HttpLoggingInterceptor()
            loging.level = HttpLoggingInterceptor.Level.BASIC
            httpClient.addInterceptor(loging)
        }
        val okHttpClient = httpClient.build()

        return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
}