package com.novemio.android.revolut.presentation.screens.converter.adapter

import android.util.Log
import android.view.View
import com.novem.lib.core.presentation.BaseViewHolder
import com.novem.lib.core.utils.isNotNullOrEmpty
import com.novemio.android.revolut.domain.currency.model.RATE_NOTHING
import com.novemio.android.revolut.domain.currency.model.Rate
import kotlinx.android.synthetic.main.converter_list_item.view.etValue
import kotlinx.android.synthetic.main.converter_list_item.view.tvTitle
import java.util.Observable
import java.util.Observer

class RateViewHolder(itemView: View, var baseRate: Rate) : BaseViewHolder<Rate>(itemView), BaseRateObserver {
	lateinit var rate: Rate
	
	override fun bind(item: Rate) {
		this.rate = item
		itemView.tvTitle.text = item.currency
		setRateValue(item.value, false)
		
	}
	
	fun getCurrentValeu() = with(itemView.etValue.text) {
		return@with if (isNotNullOrEmpty()) toString().toDouble() else RATE_NOTHING
	}
	
	fun setRateValue(value: Double, update: Boolean = true) {
		Log.d("MILAN", "${rate.currency} setRateValue: $update")
		rate.value = value
		if (baseRate.currency != rate.currency) {
			updateEditText(baseRate.toCurrencyValue(rate.value))
		} else {
			updateEditText(baseRate.toCurrencyValue())
			
		}
		
	}
	
	override fun onBaseRateChanged(newRate: Rate) {

		baseRate = newRate
		if (baseRate.currency != rate.currency) {
			Log.d("MILAN", "onBaseRateChanged: ")
			updateEditText(baseRate.toCurrencyValue(rate.value))
		}
	}
	
	private fun updateEditText(value: String) {
		Log.d("MILAN", "updateEditText: $value   $baseRate vs $rate")
		itemView.etValue.setText(value)
	}
	
	private fun Rate.toCurrencyValue(multiplier: Double = 1.0): String {
		return if (this.value == RATE_NOTHING) "" else (this.value * multiplier).toString()
	}
	
}
