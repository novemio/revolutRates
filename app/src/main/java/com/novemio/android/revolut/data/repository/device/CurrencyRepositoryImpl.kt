package com.novemio.android.revolut.data.repository.device

import com.novemio.android.revolut.data.network.api.currency.CurrencyApi
import com.novemio.android.revolut.domain.currency.CurrencyRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by novemio on 2/16/20.
 */
class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi
) : CurrencyRepository {


    override fun getCurrencyRate(currency: String): Single<Any> {
        return currencyApi.getCurrencyRate(currency)
            .subscribeOn(Schedulers.io())
    }

}