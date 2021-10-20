package com.example.cartrack.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cartrack.data.model.Company
import com.example.cartrack.data.model.User

@Database(entities = [User::class, Company::class], version = 1, exportSchema = false)
abstract class CartrackDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}