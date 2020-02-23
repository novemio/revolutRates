package com.novemio.android.revolut.domain.currency

import io.reactivex.Single

/**
 * Created by novemio on 2/16/20.
 */
interface CurrencyRepository {


    fun getCurrencyRate(currency:String): Single<Any>
}