package com.example.cartrack.di

import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.DataSource
import com.example.cartrack.data.local.RoomDatabaseStorage
import com.example.cartrack.data.remote.AppRemoteStorage
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<DataSource>(named(Scopes.REMOTE)) { AppRemoteStorage(get()) }
    single<DataSource>(named(Scopes.LOCAL)) { RoomDatabaseStorage(get()) }
    single { AppDataRepository(get(), get(named(Scopes.REMOTE)), get(named(Scopes.LOCAL)), get()) }
}
