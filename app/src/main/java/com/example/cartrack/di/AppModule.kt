package com.example.cartrack.di

import com.example.cartrack.global.AppResourceProvider
import com.example.cartrack.global.ResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
//    single {
//        GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .setDateFormat(Constants.APP_DATE_TIME_FORMAT)
////                .registerTypeAdapter(Observation::class.java, ObservationDeserializer())
//                .setLenient()
//                .create()
//    }
    single<ResourceProvider> { AppResourceProvider(androidContext()) }
//    single<SchedulerProvider> { AppSchedulerProvider() }
//    single { AppWorkManager() }
//    single { AppNotificationManager(androidContext()) }
}