package com.novemio.android.revolut.presentation.screens.converter.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import com.novem.lib.core.presentation.BaseAdapter
import com.novem.lib.core.presentation.BaseViewHolder
import com.novem.lib.core.utils.isNotNullOrEmpty
import com.novemio.android.revolut.R
import com.novemio.android.revolut.domain.currency.model.RATE_NOTHING
import com.novemio.android.revolut.domain.currency.model.Rate
import kotlinx.android.synthetic.main.converter_list_item.view.etValue

/**
 * Created by novemio on 2/17/20.
 */
class ConverterAdapter(baseRate: Rate) : BaseAdapter<Rate>() {
	
	private val currentValueObservable = BaseRateObservable(baseRate)
	var onRateClick: ((Rate, currentValue: Double) -> Unit)? = null
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Rate> {
		
		val view = parent.inflate(R.layout.converter_list_item)
		return RateViewHolder(view, currentValueObservable.getRate()).apply {
			itemView.etValue.doAfterTextChanged {
				if (adapterPosition == 0 && itemView.etValue.isFocusable) {
					val newValue = if (it.isNotNullOrEmpty()) it.toString().toDouble() else RATE_NOTHING
					currentValueObservable.updateBaseRate(Rate(rate.currency, newValue))
				}
			}
		}
	}
	
	override fun onBindViewHolder(holder: BaseViewHolder<Rate>, position: Int) {
		val item = getItemOnPosition(position)
		val rateHolder = holder as RateViewHolder
		super.onBindViewHolder(holder, position)
		currentValueObservable.addObserver(rateHolder)
		
		onRateClick?.let { click ->
			
			with(rateHolder) {
				itemView.setOnClickListener {
					if (position != 0) {
						rateHolder.baseRate = rate
						click.invoke(item, getCurrentValeu())
					}
				}
			}
		}
		
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



