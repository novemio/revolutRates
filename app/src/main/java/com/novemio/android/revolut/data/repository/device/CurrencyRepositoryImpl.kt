package com.novemio.android.revolut.data.repository.device

import com.novemio.android.revolut.data.network.api.currency.CurrencyApi
import com.novemio.android.revolut.data.network.api.currency.model.toDomain
import com.novemio.android.revolut.domain.currency.CurrencyRepository
import com.novemio.android.revolut.domain.currency.model.CurrencyRates
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by novemio on 2/16/20.
 */

private val TAG by lazy { CurrencyRepositoryImpl::class.java.simpleName }

class CurrencyRepositoryImpl @Inject constructor(
	private val currencyApi: CurrencyApi
) : CurrencyRepository {
	
	override fun getCurrencyRate(currency: String): Single<CurrencyRates> {
		return currencyApi.getCurrencyRate(currency)
			.map { it.toDomain() }
			.subscribeOn(Schedulers.io())
	}
	
}