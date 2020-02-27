package com.novemio.android.revolut.data.network

import com.novemio.android.revolut.data.network.api.rates.RatesApi
import com.novemio.android.revolut.data.network.retrofit.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by novemio on 7/5/19.
 */

@Module(includes = [RetrofitModule::class])
class NetworkModule {


    @Singleton
    @Provides
    internal fun provideCurrencyApi(retrofit: Retrofit): RatesApi {
        return retrofit.create(RatesApi::class.java)
    }

}