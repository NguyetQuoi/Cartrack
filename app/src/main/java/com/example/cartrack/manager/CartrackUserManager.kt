package com.example.cartrack.manager

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.example.cartrack.data.model.User
import com.example.cartrack.data.pref.PreferenceStorage
import com.example.cartrack.global.UserThrowable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers
import okhttp3.Request
import timber.log.Timber

/**
 * A support for AWS Cognito User
 *
 * @author quoi.tran@com.zyrous.com
 * @date 05.19.2019
 */

class CartrackUserManager(_context: Context, preferenceStorage: PreferenceStorage) : UserManager {
    override fun signIn(id: String, pw: String, name: String): Single<String> {
        return signInObservable(id, pw, name)
            .subscribeOn(Schedulers.io())
    }

    override fun signOut() {
        TODO("implement later")
    }

    private fun signInObservable(id: String, pw: String, name: String): Single<String> {
        return Single.create {
//            AWSMobileClient.getInstance().signIn(id, pw, null, object : Callback<SignInResult> {
//                override fun onResult(result: SignInResult?) {
//                    processSignInResult(it, result, id, name)
//                }
//
//                override fun onError(e: Exception?) {
//                    processError(it, e)
//                }
//            })
        }
    }
}