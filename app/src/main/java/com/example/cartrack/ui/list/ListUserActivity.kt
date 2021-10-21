package com.example.cartrack.ui.list

import android.os.Bundle
import com.example.cartrack.R
import com.example.cartrack.base.BaseActivity
import com.example.cartrack.databinding.ActivityListUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [ListUserActivity]
 * @author n.quoi
 * @date 10.20.2021
 */

class ListUserActivity : BaseActivity<ListUserViewModel, ActivityListUserBinding>() {
    override val viewModel: ListUserViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_list_user

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        viewModel.notifyDataUserChange()
    }
}
