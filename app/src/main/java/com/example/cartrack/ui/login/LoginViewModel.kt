package com.example.cartrack.ui.login

import androidx.lifecycle.viewModelScope
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.model.Account
import com.example.cartrack.manager.UserManager
import com.example.cartrack.util.rx.SchedulerProvider
import io.reactivex.Observable
import kotlinx.coroutines.launch

/**
 * LoginViewModel for [LoginActivity]
 * @author n.quoi
 * @date 10.21.2021
 */

class LoginViewModel(
    private val appDataRepository: AppDataRepository,
    private val schedulerProvider: SchedulerProvider,
    userManager: UserManager
) :
    BindingViewModel(userManager) {
    init {

    }
}
