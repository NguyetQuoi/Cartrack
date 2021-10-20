package com.example.cartrack.ui.detail

import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.manager.UserManager

/**
 * DetailViewModel for [UserDetailActivity]
 * @author n.quoi
 * @date 10.19.2021
 */

class UserDetailViewModel(userManager: UserManager) : BindingViewModel(userManager) {

    init {
    }

    fun onBackClicked() {
        finishActivity()
    }
}
