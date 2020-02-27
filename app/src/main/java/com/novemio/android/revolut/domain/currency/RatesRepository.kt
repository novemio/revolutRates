package com.novemio.android.revolut.domain.currency

import com.novemio.android.revolut.domain.currency.model.CurrencyRates
import io.reactivex.Single

/**
 * Created by novemio on 2/16/20.
 */
interface RatesRepository {


    fun getCurrencyRate(currency:String): Single<CurrencyRates>
}