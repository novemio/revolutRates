package com.novemio.android.revolut.data.network.retrofit

import com.novemio.android.revolut.data.local.authorization.AuthorizationCache
import com.novemio.android.revolut.data.network.NetworkEventBus
import com.novemio.android.revolut.data.network.api.currency.model.CurrencyRateRaw
import com.novemio.android.revolut.data.network.api.currency.model.CurrencyRateRawAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
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

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(CurrencyRateRawAdapter())
            .build()
    }


}