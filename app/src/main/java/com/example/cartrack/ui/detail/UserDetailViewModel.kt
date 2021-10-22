package com.example.cartrack.ui.detail

import androidx.lifecycle.MutableLiveData
import com.example.cartrack.base.BindingViewModel
import com.example.cartrack.data.model.UserObject
import com.example.cartrack.manager.UserManager
import com.google.android.gms.maps.model.LatLng

/**
 * DetailViewModel for [UserDetailActivity]
 * @author n.quoi
 * @date 10.21.2021
 */

class UserDetailViewModel(userManager: UserManager) : BindingViewModel(userManager) {

    private var user: UserObject? = null

    var isMyLocationButtonClick = MutableLiveData(false)

    /**
     * Set user for viewModel
     * @param user [UserObject] location
     */
    fun setData(user: UserObject) {
        this.user = user
    }

    fun onMyLocationButtonClick(){
        isMyLocationButtonClick.value = true
    }
}
