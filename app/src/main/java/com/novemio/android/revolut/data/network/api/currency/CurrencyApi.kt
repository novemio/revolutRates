package com.novemio.android.revolut.data.network.api.currency

import com.novemio.android.revolut.data.network.api.currency.model.CurrencyRateRaw
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyApi {


    @GET("latest")
    fun getCurrencyRate(@Query("base") baseCurrency: String): Single<CurrencyRateRaw>


}