package com.novemio.android.revolut.data.repository.rates

import com.novemio.android.revolut.data.network.api.rates.RatesApi
import com.novemio.android.revolut.data.network.api.rates.model.toDomain
import com.novemio.android.revolut.domain.currency.RatesRepository
import com.novemio.android.revolut.domain.currency.model.CurrencyRates
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RatesRepositoryImpl @Inject constructor(
	private val ratesApi: RatesApi
) : RatesRepository {
	
	override fun getCurrencyRate(currency: String): Single<CurrencyRates> {
		return ratesApi.getCurrencyRate(currency)
			.map { it.toDomain() }
			.subscribeOn(Schedulers.io())
	}
	
}