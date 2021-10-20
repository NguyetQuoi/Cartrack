package com.example.cartrack.di

import androidx.room.Room
import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.DataSource
import com.example.cartrack.data.local.CarTrackDatabase
import com.example.cartrack.data.local.RoomDatabaseStorage
import com.example.cartrack.data.remote.AppRemoteStorage
import com.example.cartrack.extention.db
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

    single {
        Room.databaseBuilder(androidContext(), CarTrackDatabase::class.java, "DB")
            .fallbackToDestructiveMigration()
            .openHelperFactory(SupportFactory(SQLiteDatabase.getBytes("PassPhrase".toCharArray())))
            .build()
    }

    single { db().accountDao() }

    single { db().userDao() }

    single<DataSource>(named(Scopes.REMOTE)) { AppRemoteStorage(get()) }
    single<DataSource>(named(Scopes.LOCAL)) {
        RoomDatabaseStorage(
            get(),
            get(),
            get()
        )
    }

    single {
        AppDataRepository(
            get(),
            get(named(Scopes.REMOTE)),
            get(named(Scopes.LOCAL)),
            get()
        )
    }
}
