package com.example.cartrack.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cartrack.data.model.Company
import com.example.cartrack.data.model.User

@Database(entities = [User::class, Company::class], version = 1, exportSchema = false)
abstract class CartrackDatabase : RoomDatabase() {
    abstract fun loginDao(): DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: CartrackDatabase? = null

        fun getDatabaseClient(context: Context): CartrackDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, CartrackDatabase::class.java, "CARTRACK_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}