package com.example.cartrack.extention

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * An extension for rx
 * @author n.quoi
 * @date 10.20.2021
 */

fun <T> Single<T>.with(): Single<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

/**
 * Add assign
 * @param disposeBag [CompositeDisposable] which will be added
 */
operator fun Disposable.plusAssign(disposeBag: CompositeDisposable) {
    disposeBag.add(this)
}
