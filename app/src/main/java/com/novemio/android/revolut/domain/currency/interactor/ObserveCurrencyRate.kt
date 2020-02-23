package com.novemio.android.revolut.domain.currency.interactor

import android.util.Log
import com.novem.lib.core.domain.BaseInteractorObservable
import com.novem.lib.core.utils.result.SealedResult
import com.novem.lib.core.utils.validate
import com.novemio.android.revolut.domain.currency.CurrencyRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by novemio on 2/16/20.
 */

private val TAG by lazy {ObserveCurrencyRate ::class.java.simpleName }

class ObserveCurrencyRate @Inject constructor(
    private val currencyApi: CurrencyRepository

) : BaseInteractorObservable<ObserveCurrencyRate.Params, Any>() {

    @Volatile
    private lateinit var baseCurrency: String
    private val LOCK = Any()

    override fun buildExecute(params: Params): Observable<SealedResult<Any>> {
        clearDisposables()
        baseCurrency = params.currency
        return Observable.interval(0, params.period, TimeUnit.SECONDS)
            .flatMapSingle {
                currencyApi.getCurrencyRate(baseCurrency)
            }
            .validate()
    }

    class Params(var currency: String, val period: Long = 1L)

}