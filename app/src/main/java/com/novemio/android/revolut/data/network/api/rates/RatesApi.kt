package com.novemio.android.revolut.data.network.api.rates

import com.novemio.android.revolut.data.network.api.rates.model.CurrencyRateRaw
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface RatesApi {


    @GET("latest")
    fun getCurrencyRate(@Query("base") baseCurrency: String): Single<CurrencyRateRaw>


}