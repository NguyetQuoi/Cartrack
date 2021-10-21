package com.example.cartrack.ui.splash

import android.os.Bundle
import com.example.cartrack.R
import com.example.cartrack.base.BaseActivity
import com.example.cartrack.databinding.ActivitySplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [SplashActivity] - show splash screen
 * @author n.quoi
 * @date 10.20.2021
 */

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {
    override val viewModel: SplashViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.mockupAccount()
    }
}
