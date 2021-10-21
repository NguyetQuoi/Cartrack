package com.example.cartrack.ui.login

import android.os.Bundle
import com.example.cartrack.R
import com.example.cartrack.base.BaseActivity
import com.example.cartrack.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [LoginActivity]
 * @author n.quoi
 * @date 10.20.2021
 */

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {
    override val viewModel: LoginViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showToast("login")
    }
}
