package com.novem.lib.core.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

	protected var adapterData: MutableList<T> = mutableListOf()

	open var onItemClick: ((T) -> Unit)? = null

	override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
		val itemData = adapterData[position]
		holder.bind(itemData)

	}



	override fun getItemCount(): Int = adapterData.size

	open fun setData(dataList: List<T>) {
		notifyChanged(adapterData, dataList).also {
			it.dispatchUpdatesTo(this)
			adapterData.clear()
			adapterData.addAll(dataList)
		}

	}

	fun getData(): List<T> = adapterData

	fun getItemOnPosition(position: Int): T = adapterData[position]

	fun getItemsFromTo(from: Int, to: Int): List<T> = adapterData.subList(from, to)

	fun ViewGroup.inflate(resId: Int, attachToRoute: Boolean = false): View =
		LayoutInflater.from(context).inflate(resId, this, attachToRoute)

	open fun notifyChanged(old: List<T>, new: List<T>) = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

		override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
			old[oldItemPosition].hashCode() == new[newItemPosition].hashCode()

		override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
			old[oldItemPosition] == new[newItemPosition]

		override fun getOldListSize() = old.size
		override fun getNewListSize() = new.size

	})
}