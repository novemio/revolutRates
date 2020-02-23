package com.novem.lib.core.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.novem.lib.core.AndroidComponent
import com.novem.lib.core.di.viewmodel.IViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class CoreActivity<VM : ViewModel, Binding : ViewDataBinding> :
	DaggerAppCompatActivity(), AndroidComponent {
	
	@Inject lateinit var viewModelFactory: IViewModelFactory

	
	protected var actionBar: ActionBar? = null
	protected lateinit var viewModel: VM
	lateinit var binding: Binding
		private set
	lateinit var localView: View
	
	@Suppress("UNCHECKED_CAST")
	internal fun getViewModel(): VM {
		val viewModelClassType = viewModelFactory.getViewModelByClass(this::class)!!
		return ViewModelProviders.of(this, viewModelFactory).get(viewModelClassType) as VM
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initBinding()
		initView()
		setObservers()
	}
	
	 protected open fun setObservers(){}
	
	private fun initBinding() {
		viewModel = getViewModel()
		binding = DataBindingUtil.setContentView(this, getViewLayout())
		binding.lifecycleOwner = this
		binding.setVariable(ViewModelIdProvider.viewModelId!!, viewModel)
		
	}
	
	@LayoutRes
	abstract fun getViewLayout(): Int
	
	abstract fun initView()
}