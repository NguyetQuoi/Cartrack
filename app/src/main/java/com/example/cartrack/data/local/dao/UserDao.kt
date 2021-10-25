package com.example.cartrack.data.local.dao

import androidx.room.*
import com.example.cartrack.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(addUser: User) : Long

    @Query("SELECT * FROM user WHERE id =:id")
    fun getUserDetail(id: Int?): User

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Delete
    fun deleteUser(deleteUser: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}