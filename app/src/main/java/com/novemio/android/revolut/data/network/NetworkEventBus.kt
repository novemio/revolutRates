package com.novemio.android.revolut.data.network

import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by novemio on 9/23/19.
 */
@Singleton
class NetworkEventBus @Inject constructor() {

	private val subject = PublishSubject.create<NetworkEvent>()
	private val subscriptionsMap = HashMap<Any, CompositeDisposable>()

	fun publishEvent(event: NetworkEvent) {
		subject.onNext(event)
	}

	fun subscribe(life: Any, function: (NetworkEvent) -> Unit): Disposable {
		val disposable = subject.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribeBy { function.invoke(it) }
		getCompositeDisposable(life).add(disposable)
		return disposable
	}

	@NonNull
	private fun getCompositeDisposable(@NonNull lifeCycle: Any): CompositeDisposable {
		var compositeDisposable: CompositeDisposable? = subscriptionsMap[lifeCycle]
		if (compositeDisposable == null) {
			compositeDisposable = CompositeDisposable()
			subscriptionsMap[lifeCycle] = compositeDisposable
		}
		return compositeDisposable
	}

}

sealed class NetworkEvent {
	object UserNotAuthorsed : NetworkEvent()
}