package com.novemio.android.revolut.presentation.screens.rates

import android.os.Bundle
import com.github.ajalt.timberkt.Timber
import com.novem.lib.core.presentation.CoreFragment
import com.novem.lib.core.utils.observeBy
import com.novemio.android.revolut.R
import com.novemio.android.revolut.databinding.FragmentRatesBinding
import com.novemio.android.revolut.domain.currency.model.Rate
import com.novemio.android.revolut.presentation.screens.rates.adapter.ConverterAdapter
import kotlinx.android.synthetic.main.fragment_rates.*
import kotlinx.android.synthetic.main.toolbar_default.*

class RatesFragment :
    CoreFragment<RatesViewModel, RatesRoutes, FragmentRatesBinding>() {

    override fun getLayoutId() = R.layout.fragment_rates

    private val adapter by lazy { ConverterAdapter(viewModel.baseRate) }
    private var currencyChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }
    override fun initView() {
        setToolbar(defaultToolbar, R.string.fragment_rates_title)
        initRatesList()
    }

    private fun initRatesList() {
        rvConverterList.adapter = adapter
        adapter.onBaseRateChanged = this::changeCurrency
    }

    override fun setObservers() {
        viewModel.data.observeBy(viewLifecycleOwner) {
            if (currencyChanged) {
                currencyChanged = false
                adapter.setData(it)
                rvConverterList.scrollToPosition(0)
            } else {
                adapter.setData(it)
            }
        }
    }

    private fun changeCurrency(rate: Rate, value: Double) {
        currencyChanged = true
        viewModel.changeBaseCurrency(rate, value)
    }


}