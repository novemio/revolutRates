package com.novem.lib.core.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView),
												   ViewHolderAdapterBinder<T>

internal interface ViewHolderAdapterBinder<T> {
	fun bind(item: T)
}