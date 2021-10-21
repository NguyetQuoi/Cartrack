package com.example.cartrack.di

import com.example.cartrack.data.pref.PreferenceStorage
import com.example.cartrack.data.pref.SharedPreferenceStorage
import com.example.cartrack.global.AppResourceProvider
import com.example.cartrack.global.Constants
import com.example.cartrack.global.ResourceProvider
import com.example.cartrack.manager.AppWorkManager
import com.example.cartrack.manager.CartrackUserManager
import com.example.cartrack.manager.UserManager
import com.example.cartrack.util.rx.AppSchedulerProvider
import com.example.cartrack.util.rx.SchedulerProvider
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<PreferenceStorage> { SharedPreferenceStorage(androidContext(), get()) }
    single {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(Constants.APP_DATE_TIME_FORMAT)
            .setLenient()
            .create()
    }
    single<ResourceProvider> { AppResourceProvider(androidContext()) }
    single<UserManager> { CartrackUserManager(androidContext(), get(), get()) }
    single<SchedulerProvider> { AppSchedulerProvider() }
    single { AppWorkManager() }
}