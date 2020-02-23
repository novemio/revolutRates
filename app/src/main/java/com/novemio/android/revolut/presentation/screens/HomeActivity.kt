package com.novemio.android.revolut.presentation.screens

import com.novemio.android.revolut.R
import com.novemio.android.revolut.databinding.ActivityLoginBinding
import com.novemio.android.revolut.presentation.base.BaseActivity

class HomeActivity : BaseActivity<HomeViewModel, ActivityLoginBinding>() {

    override fun getViewLayout(): Int = R.layout.activity_login

    override fun initView() {}

}
