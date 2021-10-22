package com.example.cartrack.data.local.dao

import androidx.room.*
import com.example.cartrack.data.model.Account
import com.example.cartrack.data.model.User

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(account: Account): Long

    @Query("SELECT * FROM account")
    suspend fun getAllAccount(): List<Account>

    @Query("SELECT COUNT(userName) FROM account WHERE userName =:userName AND password =:password")
    suspend fun getAccount(userName: String, password: String): Int

    @Query("SELECT * FROM account WHERE userName =:userName AND password =:password LIMIT 1")
    suspend fun getCurrentAccount(userName: String, password: String): Account
}