package com.novem.lib.core.domain

import com.novem.lib.core.utils.result.SealedResult
import io.reactivex.Observable

/**
 * Created by novemio on 7/10/19.
 */
abstract class BaseInteractorObservable<Params, Result> : BaseInteractor() {

	abstract fun buildExecute(params: Params): Observable<SealedResult<Result>>

	fun execute(params: Params) = Mapper(params)

	fun execute(params: Params, result: (SealedResult<Result>) -> Unit) {
		buildExecute(params).subscribeDispose {
			result(it)
		}
	}

	inner class Mapper(private val params: Params) {

		fun <M> map(mapper: (Result) -> M, result: (SealedResult<M>) -> Unit) {
			buildExecute(params).map { sealedResult -> sealedResult.map { mapper(it) } }
				.subscribeDispose {
					result(it)
				}
		}
	}


}

