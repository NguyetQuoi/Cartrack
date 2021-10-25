package com.example.cartrack.ui.detail

import androidx.lifecycle.MutableLiveData
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.model.User
import com.example.cartrack.manager.UserManager

/**
 * DetailViewModel for [UserDetailActivity]
 * @author n.quoi
 * @date 10.21.2021
 */

class UserDetailViewModel(userManager: UserManager) : BindingViewModel(userManager) {

    private var user: User? = null

    var isMyLocationButtonClick = MutableLiveData(false)

    /**
     * Set user for viewModel
     * @param user [User] location
     */
    fun setData(user: User) {
        this.user = user
    }

    fun onMyLocationButtonClick(){
        isMyLocationButtonClick.value = true
    }
}
