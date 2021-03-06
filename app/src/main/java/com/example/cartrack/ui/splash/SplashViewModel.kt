package com.example.cartrack.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.model.Account
import com.example.cartrack.data.pref.PreferenceStorage
import com.example.cartrack.manager.UserManager
import com.example.cartrack.ui.list.ListUserActivity
import com.example.cartrack.ui.login.LoginActivity
import com.example.cartrack.util.rx.SchedulerProvider
import io.reactivex.Observable
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * SplashViewModel for [SplashActivity]
 * @author n.quoi
 * @date 10.20.2021
 */

class SplashViewModel(
    private val appDataRepository: AppDataRepository,
    private val schedulerProvider: SchedulerProvider,
    private val preferenceStorage: PreferenceStorage,
    userManager: UserManager
) :
    BindingViewModel(userManager) {
    init {
        mockupAccount()
    }

    fun mockupAccount() {
        val mockupAcc = Account(1, "MochiHeoQuay", "moChi020#abc", "Singapore")
        mockupAcc.let {
            viewModelScope.launch {
                appDataRepository.mockUpAccount(it)
                    .observeOn(schedulerProvider.ui())
                    .subscribe({
                        Timber.d("Mock up database: %s", it.toString())
                        checkUserState()
                    }, {
                        handleError(it)
                    })
            }
        }
    }

    private fun checkUserState() {
        if (preferenceStorage.user == null) {
            goToLoginScreen()
            return
        }

        goToListUserScreen()
    }

    private fun goToLoginScreen() {
        startActivity(LoginActivity::class.java, finish = true, clearTask = true)
    }

    private fun goToListUserScreen() {
        startActivity(ListUserActivity::class.java, finish = true, clearTask = true)
    }
}
