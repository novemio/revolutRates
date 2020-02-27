package com.novemio.android.revolut.presentation.base


import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.novem.lib.core.presentation.CoreActivity


/**
 * Created by novemio on 2/16/20.
 */
abstract class BaseActivity<VM : ViewModel, Binding : ViewDataBinding> :
    CoreActivity<VM, Binding>()