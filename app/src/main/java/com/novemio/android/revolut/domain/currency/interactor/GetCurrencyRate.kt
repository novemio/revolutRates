package com.novemio.android.revolut.domain.currency.interactor

import com.novem.lib.core.domain.BaseInteractorSingle
import com.novem.lib.core.utils.validate
import com.novemio.android.revolut.domain.currency.CurrencyRepository
import javax.inject.Inject

/**
 * Created by novemio on 2/16/20.
 */
class GetCurrencyRate @Inject constructor(
    private val currencyApi: CurrencyRepository

) : BaseInteractorSingle<String, Any>() {

    override fun buildExecute(params: String) = currencyApi.getCurrencyRate(params).validate()

}