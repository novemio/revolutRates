package com.novemio.android.revolut.data.network.retrofit

import com.novemio.android.revolut.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Created by novemio on 7/5/19.
 */

private const val TIMEOUT = 10L
private const val POOL_SIZE = 5
private const val KEEP_ALIVE = 300000L

class OkHttpBuilderFactory {

    fun get(): OkHttpClient.Builder {

        return Builder().addInterceptor(getHeaderInterceptor())
            .addNetworkInterceptor(getLogInterceptor())
            .connectTimeout(TIMEOUT, SECONDS)
            .readTimeout(TIMEOUT, SECONDS)
            .writeTimeout(TIMEOUT, SECONDS)
            .connectionPool(ConnectionPool(POOL_SIZE, KEEP_ALIVE, MILLISECONDS))
            .retryOnConnectionFailure(true)
    }


    private fun getHeaderInterceptor(): Interceptor {
        return invoke { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")

            chain.proceed(request.build())
        }
    }

    private fun getLogInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    }
}





