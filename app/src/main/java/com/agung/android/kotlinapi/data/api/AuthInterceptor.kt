package com.agung.android.kotlinapi.data.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by agung on 07/02/18.
 */
class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
                .addHeader("Authorization", "Basic ***")
                .method(original.method(), original.body())
                .build()
        return chain.proceed(request)
    }
}