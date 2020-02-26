package com.novemio.android.revolut.presentation.screens.rates.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.novem.lib.core.presentation.BaseAdapter
import com.novem.lib.core.presentation.BaseViewHolder
import com.novemio.android.revolut.R
import com.novemio.android.revolut.domain.currency.model.Rate
import kotlinx.android.synthetic.main.list_item_rates.view.etValue

/**
 * Created by novemio on 2/22/20.
 */

class ConverterAdapter(baseRate: Rate) : BaseAdapter<Rate>() {
	
	private val currentValueObservable = BaseRateObservable(baseRate)
	lateinit var onBaseRateChanged: ((Rate, currentValue: Double) -> Unit)
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Rate> {
		
		val view = parent.inflate(R.layout.list_item_rates)
		return RateViewHolder(view, currentValueObservable.getRate()).apply {
			onRateClick = onBaseRateChanged
			itemView.etValue.addTextChangedListener(NumberTextWatcher(itemView.etValue) {
				if (adapterPosition == 0 && itemView.etValue.hasFocus()) {
					currentValueObservable.updateBaseRate(Rate(rate.currency, it))
				}
			})
		}
	}
	
	override fun onBindViewHolder(holder: BaseViewHolder<Rate>, position: Int) {
		val rateHolder = holder as RateViewHolder
		super.onBindViewHolder(holder, position)
		currentValueObservable.addObserver(rateHolder)
	}
	
	override fun onBindViewHolder(holder: BaseViewHolder<Rate>, position: Int, payloads: MutableList<Any>) {
		if (payloads.isEmpty()) {
			super.onBindViewHolder(holder, position, payloads)
		} else {
			val bundle = payloads[0] as Bundle
			(holder as RateViewHolder).setRateValue(bundle.getDouble("value"))
		}
	}
	
	override fun notifyChanged(old: List<Rate>, new: List<Rate>) =
		DiffUtil.calculateDiff(ConverterDiffCallback(old, new))
	
}




