package com.example.cartrack.base

import androidx.lifecycle.ViewModel
import com.example.cartrack.extention.plusAssign
import com.example.cartrack.global.RxProperty
import io.reactivex.disposables.CompositeDisposable

/**
 * An open class for general of view-model
 * @author n.quoi
 * @date 10.18.2021
 */
open class BaseViewModel : ViewModel(), Destroyable {

    protected val disposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        onDestroy()
    }

    override fun onDestroy() {
        disposeBag.dispose()
    }
}

open class BaseItemViewModel(selected: Boolean = false) : BaseViewModel() {
    var isSelected = RxProperty(selected)
    var onSelectedChanged: ((Boolean) -> Unit)? = null

    init {
        isSelected.asObservable().subscribe {
            onSelectedChanged?.invoke(it)
        } += disposeBag
    }
}