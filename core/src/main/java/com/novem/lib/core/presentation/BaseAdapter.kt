package com.novem.lib.core.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

	private var data: MutableList<T> = mutableListOf()

	var onItemClick: ((T) -> Unit)? = null

	override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
		val itemData = data[position]
		holder.bind(itemData)
		onItemClick?.let { click ->
			holder.itemView.setOnClickListener {
				click.invoke(itemData)
			}
		}
	}

	override fun getItemCount(): Int = data.size

	fun setData(dataList: List<T>) {
		notifyChanged(data, dataList).also {
			data.clear()
			data.addAll(dataList)
			it.dispatchUpdatesTo(this)
		}

	}


	fun getData(): List<T> = data

	fun getItemOnPosition(position: Int): T = data[position]

	fun getItemsFromTo(from: Int, to: Int): List<T> = data.subList(from, to)

	private fun notifyChanged(old: List<T>, new: List<T>) = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

		override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
			old[oldItemPosition].hashCode() == new[newItemPosition].hashCode()

		override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
			old[oldItemPosition] == new[newItemPosition]

		override fun getOldListSize() = old.size
		override fun getNewListSize() = new.size

	})



	fun ViewGroup.inflate(resId: Int, attachToRoute: Boolean = false): View =
		LayoutInflater.from(context).inflate(resId, this, attachToRoute)

}