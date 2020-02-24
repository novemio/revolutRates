package com.novemio.android.revolut.presentation.screens.converter

import com.github.ajalt.timberkt.Timber
import com.novem.lib.core.presentation.CoreFragment
import com.novem.lib.core.utils.observeBy
import com.novemio.android.revolut.R
import com.novemio.android.revolut.databinding.FragmentConverterBinding
import com.novemio.android.revolut.domain.currency.model.Rate
import com.novemio.android.revolut.presentation.screens.converter.adapter.ConverterAdapter
import kotlinx.android.synthetic.main.fragment_converter.*

class ConverterFragment :
    CoreFragment<ConverterViewModel, ConverterRoutes, FragmentConverterBinding>() {

    override fun getLayoutId() = R.layout.fragment_converter

    private val adapter by lazy { ConverterAdapter(viewModel.baseRate) }
    private var currencyChanged = false
    override fun initView() {
        rvConverterList.adapter = adapter
        adapter.onRateClick = this::changeCurrency
    }

    override fun setObservers() {

        viewModel.data.observeBy(viewLifecycleOwner) {
            if (currencyChanged) {
                currencyChanged = false
                adapter.setData(it)
                rvConverterList.scrollToPosition(0)
            }else{
                adapter.setData(it)
            }

        }
    }


    fun changeCurrency(rate: Rate,value:Double) {
        Timber.d {"MILAN"+rate.toString()}
        currencyChanged=true
        viewModel.changeBaseCurrency(rate,value)
    }


}
