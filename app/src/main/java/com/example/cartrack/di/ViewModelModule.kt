package com.example.cartrack.di

import com.example.cartrack.ui.detail.UserDetailViewModel
import com.example.cartrack.ui.list.ListUserViewModel
import com.example.cartrack.ui.login.LoginViewModel
import com.example.cartrack.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserDetailViewModel(get()) }
    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel {LoginViewModel(get(), get(), get())}
    viewModel { ListUserViewModel(get(), get(), get()) }
}