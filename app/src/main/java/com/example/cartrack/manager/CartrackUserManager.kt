package com.example.cartrack.manager

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cartrack.R
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.model.Account
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.example.cartrack.data.model.User
import com.example.cartrack.data.pref.PreferenceStorage
import com.example.cartrack.global.UserThrowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Request
import timber.log.Timber

/**
 * A support for AWS Cognito User
 *
 * @author quoi.tran@com.zyrous.com
 * @date 05.19.2019
 */

class CartrackUserManager(
    context: Context,
    preferenceStorage: PreferenceStorage,
    private val appDataRepository: AppDataRepository,
) : CartrackUserInit(context, preferenceStorage), UserManager {

    override suspend fun signIn(username: String, password: String, name: String): Single<String> {
        return signInObservable(username, password)
            .subscribeOn(Schedulers.io())
    }

    override suspend fun signOut() {
        TODO("implement later")
    }

    private suspend fun signInObservable(username: String, password: String): Single<String> {
        return Single.create {
        }
    }
}