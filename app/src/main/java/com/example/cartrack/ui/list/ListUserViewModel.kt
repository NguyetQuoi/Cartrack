package com.example.cartrack.ui.list

import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.manager.UserManager
import com.example.cartrack.util.rx.SchedulerProvider

/**
 * ListUserViewModel for [ListUserActivity]
 * @author n.quoi
 * @date 10.21.2021
 */


class ListUserViewModel(userManager: UserManager) : BindingViewModel(userManager) {

    init {
    }

    fun onBackClicked() {
        finishActivity()
    }
}
