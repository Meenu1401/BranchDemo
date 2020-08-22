package com.demo.branch.api

import okhttp3.OkHttpClient

object NetworkUtils {

    val httpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }
            .build()

    fun getHttpClient(httpClient: OkHttpClient.Builder): OkHttpClient {
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }
        return httpClient.build()
    }

}
