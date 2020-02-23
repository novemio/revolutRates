package com.novem.lib.core.domain

import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

interface IDisposable {

    val compositeDisposable: CompositeDisposable

    fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    fun dispose() = compositeDisposable.dispose()

    fun clearDisposables() = compositeDisposable.clear()

    fun Completable.subscribeDispose(
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
    ): Disposable {
        return subscribeBy(onError, onComplete).apply { compositeDisposable.add(this) }
    }

    fun <T : Any> Maybe<T>.subscribeDispose(
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {},
        onSuccess: (T) -> Unit = {}
    ): Disposable =
        subscribeBy(onError, onComplete, onSuccess).apply { compositeDisposable.add(this) }

    fun <T : Any> Single<T>.subscribeDispose(
        onError: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    ): Disposable = subscribeBy(onError, onSuccess).apply { compositeDisposable.add(this) }

    fun <T : Any> Observable<T>.subscribeDispose(
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {}
    ): Disposable = subscribeBy(onError, onComplete, onNext).apply { compositeDisposable.add(this) }

    fun <T : Any> Flowable<T>.subscribeDispose(
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {},
        onNext: (T) -> Unit = {}
    ): Disposable = subscribeBy(onError, onComplete, onNext).apply { compositeDisposable.add(this) }

}