package com.example.cartrack.extention

import com.example.cartrack.data.local.CarTrackDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.scope.Scope

/**
 * An extension for Koin
 * @author n.quoi
 * @date 10.20.2021
 */

// An extension function to access our database type.
// If you are using the suggestion above
// RakeDatabase would be replaced by IRakebDatabase
fun Scope.db() = get<CarTrackDatabase>()

