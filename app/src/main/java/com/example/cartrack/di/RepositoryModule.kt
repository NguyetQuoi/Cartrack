package com.example.cartrack.di

import android.app.Application
import androidx.room.Room
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.DataSource
import com.example.cartrack.data.local.CartrackDatabase
import com.example.cartrack.data.local.RoomDatabaseStorage
import com.example.cartrack.data.local.UserDao
import com.example.cartrack.data.remote.AppRemoteStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    fun provideDataBase(application: Application): CartrackDatabase {
        return Room.databaseBuilder(application, CartrackDatabase::class.java, "CARTRACK_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: CartrackDatabase): UserDao {
        return dataBase.userDao
    }

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
    single<DataSource>(named(Scopes.REMOTE)) { AppRemoteStorage(get()) }
    single<DataSource>(named(Scopes.LOCAL)) { RoomDatabaseStorage(get(), get(), get()) }
    single { AppDataRepository(get(named(Scopes.REMOTE)), get(named(Scopes.LOCAL))) }

}
