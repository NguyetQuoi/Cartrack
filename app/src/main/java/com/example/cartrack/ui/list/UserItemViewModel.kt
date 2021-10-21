package com.example.cartrack.ui.list

import androidx.databinding.ObservableField
import com.example.cartrack.base.BaseItemViewModel
import com.example.cartrack.data.model.UserObject

/**
 * ViewModel for User Item
 *
 * @author n.quoi
 * @date 10.21.2021
 */
class UserItemViewModel(val user: UserObject) : BaseItemViewModel(false) {
    val userName = ObservableField(user.name)
    val company = ObservableField(user.company.name)
}
