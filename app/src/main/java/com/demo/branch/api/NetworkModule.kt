package com.demo.branch.api


import com.demo.branch.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private var client: OkHttpClient.Builder? = null

    constructor()
    constructor(client: OkHttpClient.Builder) {
        this.client = client
    }

    @Provides
    @Singleton
    internal fun provideCall(): Retrofit {
        val retrofit: Retrofit
        if (client == null) {
            client = OkHttpClient.Builder()
                .apply {
                    addInterceptor(Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        return@Interceptor chain.proceed(builder.build())
                    })
                }
                .connectTimeout(NETWORK_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                .readTimeout(NETWORK_TIMEOUT_IN_SEC, TimeUnit.SECONDS)

            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(NetworkUtils.getHttpClient(client!!))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        } else {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(NetworkUtils.httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        return retrofit
    }

    @Provides
    @Singleton
    internal fun providesNetworkService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    internal fun providesRetrofitHelper(apiHelper: APIHelper): NetworkAPIs {
        return apiHelper
    }

    companion object {
        const val NETWORK_TIMEOUT_IN_SEC: Long = 30
    }

}
