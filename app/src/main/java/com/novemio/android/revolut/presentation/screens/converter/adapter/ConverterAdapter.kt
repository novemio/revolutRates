package com.novemio.android.revolut.presentation.screens.converter.adapter

import android.view.View
import android.view.ViewGroup
import com.novem.lib.core.presentation.BaseAdapter
import com.novem.lib.core.presentation.BaseViewHolder
import com.novemio.android.revolut.R

/**
 * Created by novemio on 2/17/20.
 */
class ConverterAdapter : BaseAdapter<CountryRateItem>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CountryRateItem> {
        val view = parent.inflate(R.layout.converter_list_item)
        return RateViewHolder(view)
    }

}


class RateViewHolder(itemView: View) : BaseViewHolder<CountryRateItem>(itemView) {
    override fun bind(item: CountryRateItem) {
        TODO()
    }

}


data class CountryRateItem(val name: String)