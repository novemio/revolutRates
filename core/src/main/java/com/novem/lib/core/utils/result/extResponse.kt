package com.novem.lib.core.utils.result

import com.novem.lib.core.utils.result.RequestError.HttpError
import com.novem.lib.core.utils.result.SealedResult.OnError
import com.novem.lib.core.utils.result.SealedResult.OnSuccess
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.BaseTestConsumer

typealias FlowableResult<T> = Flowable<SealedResult<T>>
typealias SingleResult<T> = Single<SealedResult<T>>
typealias SingleResultUnit = Single<SealedResult<Unit>>
typealias ObservableResult<T> = Observable<SealedResult<T>>

fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.assertResultSuccess(): BaseTestConsumer<T, U> {
	return assertNoErrors().assertComplete().assertValue {
		it is OnSuccess<*>
	}
}

inline fun <reified E> BaseTestConsumer<*, *>.assertResultSuccessValue(e: E): BaseTestConsumer<*, *> =
	assertNoErrors().assertComplete().assertValue {
		((it as OnSuccess<*>).data as E) == e

	}

inline fun <reified E> BaseTestConsumer<*, *>.assertResultSuccessValue(crossinline block: (E) -> Boolean): BaseTestConsumer<*, *> =
	assertNoErrors().assertComplete().assertValue {
		block.invoke(((it as OnSuccess<*>).data as E))
	}

inline fun <reified E> BaseTestConsumer<*, *>.assertResultErrorValue(): BaseTestConsumer<*, *> =
	assertNoErrors().assertComplete().assertValue {
		(it as OnError<*>).error is E
	}

inline fun <reified E> BaseTestConsumer<*, *>.assertErrorValue(): BaseTestConsumer<*, *> =
	assertNoErrors().assertComplete().assertValue {
		it is E
	}

fun BaseTestConsumer<*, *>.assertHttpErrorValue(code: Int): BaseTestConsumer<*, *> =
	assertNoErrors().assertComplete().assertValue {
		((it as OnError<*>).error as HttpError).code == code
	}

fun <T, U : BaseTestConsumer<T, U>> BaseTestConsumer<T, U>.assertResultError(): BaseTestConsumer<T, U> {
	return assertNoErrors().assertComplete().assertValue {
		it is OnError<*>
	}
}

fun <T> Single<T>.toResultSuccess(): SingleResult<T> = this.map { (OnSuccess(it)) }
fun <T> Single<T>.toResultError(): SingleResult<T> =
	this.map<SealedResult<T>> { OnSuccess(it) }
		.onErrorReturn {
			OnError(RequestErrorParser.parse(it))
		}

fun <T> SingleResult<T>.error(error: RequestError) = SingleResult.just(OnError<T>(error))

fun <R, M> SingleResult<R>.sealedMap(block: (R) -> M): SingleResult<M> = this.map { it.map(block) }







