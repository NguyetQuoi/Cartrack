package com.example.cartrack.di

import com.example.cartrack.ui.detail.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserDetailViewModel(get()) }
}