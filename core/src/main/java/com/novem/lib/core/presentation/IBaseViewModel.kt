package com.novem.lib.core.presentation

import com.novem.lib.core.domain.BaseInteractor
import com.novem.lib.core.domain.BaseInteractorFlowable
import com.novem.lib.core.domain.BaseInteractorObservable
import com.novem.lib.core.domain.BaseInteractorSingle
import com.novem.lib.core.utils.result.SealedResult

interface IBaseViewModel {

    val interactors: MutableSet<BaseInteractor>

    fun clearInteractors(){
        interactors.forEach { it.clearDisposables() }
    }

    fun <P, R> BaseInteractorSingle<P, R>.executeBy(params: P, result: (SealedResult<R>) -> Unit) {
        interactors.add(this)
        this.execute(params, result)
    }

    fun <P, R> BaseInteractorFlowable<P, R>.executeBy(
        params: P,
        result: (SealedResult<R>) -> Unit
    ) {
        interactors.add(this)
        this.execute(params, result)
    }

    fun <P, R> BaseInteractorObservable<P, R>.executeBy(
        params: P,
        result: (SealedResult<R>) -> Unit
    ) {
        interactors.add(this)
        this.execute(params, result)
    }
}