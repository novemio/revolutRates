package com.novemio.android.revolut.data.network.retrofit

import com.novem.lib.core.data.network.SessionExpired
import com.novemio.android.revolut.BuildConfig
import com.novemio.android.revolut.data.local.authorization.AuthorizationCache
import com.novemio.android.revolut.data.network.NetworkEvent
import com.novemio.android.revolut.data.network.NetworkEventBus
import okhttp3.*
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Created by novemio on 7/5/19.
 */

private const val TIMEOUT = 30L
private const val POOL_SIZE = 5
private const val KEEP_ALIVE = 300000L

class OkHttpBuilderFactory constructor(
    private val authorizationCache: AuthorizationCache,
    private val networkEventBus: NetworkEventBus
) {

    fun get(): OkHttpClient.Builder {

        return Builder().addInterceptor(getHeaderInterceptor(authorizationCache))
            .authenticator(getAuthenticator(authorizationCache))
            .addNetworkInterceptor(getLogInterceptor())
            .connectTimeout(TIMEOUT, SECONDS)
            .readTimeout(TIMEOUT, SECONDS)
            .writeTimeout(TIMEOUT, SECONDS)
            .connectionPool(ConnectionPool(POOL_SIZE, KEEP_ALIVE, MILLISECONDS))
            .retryOnConnectionFailure(true)
    }

    private fun getAuthenticator(authorizationCache: AuthorizationCache) = object : Authenticator {

        override fun authenticate(route: Route?, response: Response): Request? {
            networkEventBus.publishEvent(NetworkEvent.UserNotAuthorsed)
            throw SessionExpired()
        }
    }

    private fun getHeaderInterceptor(authorizationCache: AuthorizationCache): Interceptor {
        return invoke { chain ->
            val token = authorizationCache.getAuthToken()
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





