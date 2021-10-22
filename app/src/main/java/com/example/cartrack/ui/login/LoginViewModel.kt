package com.example.cartrack.ui.login

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import androidx.room.util.StringUtil
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.model.Account
import com.example.cartrack.data.pref.PreferenceStorage
import com.example.cartrack.extention.plusAssign
import com.example.cartrack.global.RxProperty
import com.example.cartrack.manager.UserManager
import com.example.cartrack.ui.list.ListUserActivity
import com.example.cartrack.util.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * LoginViewModel for [LoginActivity]
 * @author n.quoi
 * @date 10.21.2021
 */

class LoginViewModel(
    private val appDataRepository: AppDataRepository,
    userManager: UserManager,
    private val schedulerProvider: SchedulerProvider,
    private val preferenceStorage: PreferenceStorage,
) : BindingViewModel(userManager) {

    val signInUsername = RxProperty<String>("MochiHeoQuay")
    val signInPassword = RxProperty<String>("moChi020#abc")
    val enableLogInButton = RxProperty(false)
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
            }).subscribe { enableLogInButton.set(it) } += disposeBag
    }

    fun onContinueLogIn() {
        signInUsername.get()?.let { username ->
            signInPassword.get()?.let { password ->
                showLoadingDialog()
                viewModelScope.launch {
                    appDataRepository.login(username, password)
                        .observeOn(schedulerProvider.ui())
                        .doFinally { dismissLoadingDialog() }
                        .subscribe({
                            loginResult(it)
                        }, {
                            handleError(it)
                        })
                }
            }
        }
    }

    private fun gotoListScreen() {
        startActivity(ListUserActivity::class.java, finish = true, clearTask = true)
    }

    private fun loginResult(result: Boolean) {
        if (result) {
            preferenceStorage.user =
                Account(1, signInUsername.get() ?: "", signInPassword.get() ?: "", "")
            gotoListScreen()
            return
        }

        showToast("Login fail due to wrong username or password. Please try again!")
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
        val format = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~\$^+=<>]).{7,20}\$"
        val pattern = Pattern.compile(
            format,
            Pattern.CASE_INSENSITIVE
        )
        val matcher = pattern.matcher(password)
        return matcher.find()
    }
}
