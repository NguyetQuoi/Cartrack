package com.example.cartrack.ui.login

import androidx.databinding.ObservableBoolean
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.extention.plusAssign
import com.example.cartrack.global.RxProperty
import com.example.cartrack.manager.UserManager
import com.example.cartrack.util.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * LoginViewModel for [LoginActivity]
 * @author n.quoi
 * @date 10.21.2021
 */

class LoginViewModel(
    private val appDataRepository: AppDataRepository, userManager: UserManager,
    private val schedulerProvider: SchedulerProvider
) : BindingViewModel(userManager) {

    val signInUsername = RxProperty<String>()
    val signInPassword = RxProperty<String>()
    val enableContinueSignInButton = RxProperty(false)
    val isTogglePassword = ObservableBoolean(false)

    init {
        Observable.combineLatest<String, String, Boolean>(
            signInUsername.asObservable(),
            signInPassword.asObservable(),
            { username, password ->
                username.isNotEmpty()
                        && password.isNotEmpty()
                        && isUsernameValid(username)
                        && isPasswordValid(password)
            }).subscribe { enableContinueSignInButton.set(it) } += disposeBag
    }

    fun onContinueSignIn() {
    }

    /**
     * check valid email
     * @param username string need to be checked
     * @return resource id of error string (wrong) or 0 (right)
     */
    private fun isUsernameValid(username: String): Boolean {
        val patter = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val matcher = patter.matcher(username)
        return username.length >= 7 && !matcher.find()
    }

    /**
     * check valid password
     * @param password which need to be checked
     * @return resource id of error string (wrong) or 0 (right)
     */
    private fun isPasswordValid(password: String): Boolean {
        //must contain upper case, lower case, numeric, special chars, min 7 chars
        val format = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~\$^+=<>]).{8,20}\$"
        val pattern = Pattern.compile(
            format,
            Pattern.CASE_INSENSITIVE
        )
        val matcher = pattern.matcher(password)
        return matcher.find()
    }
}
