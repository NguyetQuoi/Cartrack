package com.example.cartrack.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.model.Account
import com.example.cartrack.manager.UserManager
import com.example.cartrack.util.rx.SchedulerProvider
import io.reactivex.Observable
import kotlinx.coroutines.launch

/**
 * DetailViewModel for [SplashActivity]
 * @author n.quoi
 * @date 10.20.2021
 */

class SplashViewModel(
    private val appDataRepository: AppDataRepository,
    private val schedulerProvider: SchedulerProvider,
    userManager: UserManager
) :
    BindingViewModel(userManager) {
    init {
        mockupAccount()
    }

    //private val mockupAcc = Account(1, "NQ", "moChi020#abc", "Singapore")

    fun mockupAccount() {
        val mockupAcc = Account(1, "NQ", "moChi020#abc", "Singapore")
        mockupAcc.let {
            viewModelScope.launch {
                showLoadingDialog()
                appDataRepository.mockUpAccount(it)
                    .observeOn(schedulerProvider.ui())
                    .doFinally { dismissLoadingDialog() }
                    .subscribe({
                        showToast(it.toString())
                    }, {
                        handleError(it)
                    })
            }
        }
    }

    fun onBackClicked() {
        finishActivity()
    }
}
