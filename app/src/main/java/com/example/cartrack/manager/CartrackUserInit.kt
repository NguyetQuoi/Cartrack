package com.example.cartrack.manager

import android.content.Context
import android.util.Patterns
import com.example.cartrack.R
import com.example.cartrack.data.model.Account
import com.example.cartrack.data.pref.PreferenceStorage
import io.reactivex.Observable
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.Pattern.compile


/**
 * Data init for CartrackUserManager
 *
 * @author n.quoi
 * @date 10.21.2021
 */
open class CartrackUserInit(context: Context, private val preferenceStorage: PreferenceStorage) {

    protected val context: Context = context.applicationContext
    protected var mCurrentUser: Account? = null
        set(value) {
            field = value
            preferenceStorage.user = value
        }

    init {
        mCurrentUser = preferenceStorage.user
    }

    /**
     * check valid email
     * @param username string need to be checked
     * @return resource id of error string (wrong) or 0 (right)
     */
    fun isUsernameValid(username: Observable<String>): Boolean {
        val p = compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val m: Matcher = p.matcher("I am a string")
        val b: Boolean = m.find()
        val result = username.map {
            (it.length >= 7) && b
        }
        return result.blockingFirst()
    }

    /**
     * check valid password
     * @param pass which want to be checked
     * @return resource id of error string (wrong) or 0 (right)
     */
    fun isPasswordValid(pass: Observable<String>): Observable<Int> {
        return pass.map { if (it.length < 6) R.string.password_not_valid else 0 }
    }
}