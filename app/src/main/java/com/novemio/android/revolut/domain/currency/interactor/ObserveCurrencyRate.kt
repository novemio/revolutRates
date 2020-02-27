package com.novemio.android.revolut.domain.currency.interactor

import com.novem.lib.core.domain.BaseInteractorObservable
import com.novem.lib.core.utils.result.SealedResult
import com.novem.lib.core.utils.validate
import com.novemio.android.revolut.domain.currency.RatesRepository
import com.novemio.android.revolut.domain.currency.model.CurrencyRates
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ObserveCurrencyRate @Inject constructor(
    private val ratesApi: RatesRepository

) : BaseInteractorObservable<ObserveCurrencyRate.Params, CurrencyRates>() {



    override fun buildExecute(params: Params): Observable<SealedResult<CurrencyRates>> {
        clearDisposables()
        return Observable.interval(0, params.period, TimeUnit.SECONDS)
            .flatMapSingle {
                ratesApi.getCurrencyRate( params.currency)
            }
            .validate()
    }

    class Params(var currency: String, val period: Long = 1L)

}