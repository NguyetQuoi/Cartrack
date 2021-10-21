package com.example.cartrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cartrack.data.local.dao.AccountDao
import com.example.cartrack.data.local.dao.UserDao
import com.example.cartrack.data.model.Account
import com.example.cartrack.data.model.User

interface ICarTrackDatabase {
    fun userDao(): UserDao
    fun accountDao(): AccountDao
}

@Database(
    entities = [User::class, Account::class],
    version = 2,
    exportSchema = false
)
abstract class CarTrackDatabase : RoomDatabase(), ICarTrackDatabase {
}