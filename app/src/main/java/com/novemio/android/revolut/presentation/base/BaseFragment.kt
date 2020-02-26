package com.novemio.android.revolut.presentation.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.novem.lib.core.di.routes.Routes
import com.novem.lib.core.presentation.CoreFragment
import com.novemio.android.revolut.presentation.base.widget.LoadingDialog

/**
 * Created by novemio on 2/26/20.
 */
abstract class BaseFragment<VM : ViewModel, Route : Routes, Binding : ViewDataBinding> :
    CoreFragment<VM, Route, Binding>() {


    private var loadingDialog: LoadingDialog? = null

    fun showLoadingDialog(value: Boolean) =
        loadingDialog().run {
            if (value) show() else dismiss()
        }

    fun loadingDialog(): LoadingDialog {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(activity!!)
        }
        return loadingDialog!!
    }

}