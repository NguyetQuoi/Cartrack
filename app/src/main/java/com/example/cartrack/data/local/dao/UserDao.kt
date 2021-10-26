package com.example.cartrack.data.local.dao

import androidx.room.*
import com.example.cartrack.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(addUser: User): Long

    @Query("SELECT * FROM user WHERE id =:id")
    suspend fun getUserDetail(id: Int?): User

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<User>

    @Delete
    suspend fun deleteUser(deleteUser: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}