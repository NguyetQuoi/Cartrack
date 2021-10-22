package com.example.cartrack.ui.list

import android.graphics.Color
import android.view.View
import androidx.databinding.ObservableField
import com.example.cartrack.base.BaseItemViewModel
import com.example.cartrack.base.Navigable
import com.example.cartrack.data.model.UserObject

/**
 * ViewModel for User Item
 *
 * @author n.quoi
 * @date 10.21.2021
 */
class UserItemViewModel(val user: UserObject, val selected: Boolean = false) :
    BaseItemViewModel(selected) {
    val userName = ObservableField(user.name)
}
