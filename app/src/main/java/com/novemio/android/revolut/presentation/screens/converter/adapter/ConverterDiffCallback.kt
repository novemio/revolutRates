package com.novemio.android.revolut.presentation.screens.converter.adapter

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.novemio.android.revolut.domain.currency.model.Rate

/**
 * Created by novemio on 2/23/20.
 */
class ConverterDiffCallback(
    private val oldList: List<Rate>,
    private val newList: List<Rate>
) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].currency == newList[newItemPosition].currency


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].value == newList[newItemPosition].value


    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldRate = oldList[oldItemPosition]
        val newRate = newList[newItemPosition]
        val diff = Bundle()
        with(diff) {
            if (oldRate.value != newRate.value) {
                putDouble("value", newRate.value)
            }
        }
        return if (diff.isEmpty) null else diff
    }


}