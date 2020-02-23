package com.novemio.android.revolut.data.network.retrofit

import com.novemio.android.revolut.data.local.authorization.AuthorizationCache
import com.novemio.android.revolut.data.network.NetworkEventBus
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by novemio on 7/5/19.
 */
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(NetworkConstants.HOST_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        authorizationCache: AuthorizationCache,
        networkEventBus: NetworkEventBus
    ): OkHttpClient {
        return OkHttpBuilderFactory(authorizationCache, networkEventBus).get().build()
    }

}