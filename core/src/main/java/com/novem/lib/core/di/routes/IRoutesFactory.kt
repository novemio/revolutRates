package com.novem.lib.core.di.routes

import com.novem.lib.core.presentation.CoreFragment

interface IRoutesFactory {

	fun get(fragmentClass: Class<out CoreFragment<*, *,*>>): Routes?
}