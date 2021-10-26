package com.example.cartrack.ui.list

import androidx.databinding.ObservableField
import com.example.cartrack.base.BaseItemViewModel
import com.example.cartrack.data.model.User

/**
 * ViewModel for User Item
 *
 * @author n.quoi
 * @date 10.21.2021
 */
class UserItemViewModel(val user: User, val selected: Boolean = false) :
    BaseItemViewModel(selected) {
    val userName = ObservableField(user.name)
}
