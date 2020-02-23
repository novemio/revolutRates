package com.novem.lib.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.novem.lib.core.AndroidComponent
import com.novem.lib.core.di.routes.IRoutesFactory
import com.novem.lib.core.di.routes.Routes
import com.novem.lib.core.di.viewmodel.IViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private val TAG: String = "BaseFragment"

abstract class CoreFragment<VM : ViewModel, Route : Routes, Binding : ViewDataBinding>
    : DaggerFragment(), AndroidComponent {

    @Inject
    lateinit var viewModelFactory: IViewModelFactory
    @Inject
    lateinit var routesFactory: IRoutesFactory

    protected lateinit var navigation: Route

    protected val actionBar: ActionBar? by lazy {
        (activity as AppCompatActivity).supportActionBar
    }

    protected lateinit var viewModel: VM
    lateinit var binding: Binding
        private set
    lateinit var localView: View

    internal fun getViewModel(): VM {
        val viewModelClassType = viewModelFactory.getViewModelByClass(this::class)!!

        return if (getActivityAsVMOwner())
            ViewModelProviders.of(activity!!, viewModelFactory).get(viewModelClassType) as VM
        else ViewModelProviders.of(this, viewModelFactory).get(viewModelClassType) as VM
    }

    protected open fun getActivityAsVMOwner(): Boolean {
        return false
    }

    @Suppress("UNCHECKED_CAST")
    fun getRoute() = routesFactory.get(this::class.java) as Route

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation = getRoute()
        viewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = if (::localView.isInitialized) {
            DataBindingUtil.getBinding(localView)!!
        } else {
            DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        }
        binding.lifecycleOwner = this
        binding.setVariable(ViewModelIdProvider.viewModelId!!, viewModel)

        localView = binding.root
        setHasOptionsMenu(true)
        return localView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObservers()

    }

    abstract fun initView()
    open fun setObservers() {}

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected fun setToolbar(
        toolbar: Toolbar, up: Boolean = false, @DrawableRes homeIndicatorIcon: Int? = null
    ) {
        setToolbar(toolbar, null, up, homeIndicatorIcon)
    }

    protected fun setToolbar(
        toolbar: Toolbar,
        @StringRes titleId: Int? = null,
        up: Boolean = false, @DrawableRes
        homeIndicatorIcon: Int? = null
    ) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        //		navigation.run {
        //			NavigationUI.setupActionBarWithNavController(activity!! as AppCompatActivity, this.navController)
        //		}
        actionBar?.apply {
            titleId?.let { this.setTitle(it) }
            homeIndicatorIcon?.let {
                setHomeAsUpIndicator(homeIndicatorIcon)
            }
            setDisplayHomeAsUpEnabled(up)
        }

    }

    protected fun setToolbarTitle(title: String) {
        actionBar?.title = title
    }

    protected fun setToolbarTitle(@StringRes titleId: Int) {
        actionBar?.setTitle(titleId)
    }

    protected fun showToast(message: String) =
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()

}