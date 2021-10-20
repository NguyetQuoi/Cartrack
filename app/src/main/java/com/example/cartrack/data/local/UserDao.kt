package com.example.cartrack.data.local

import androidx.room.*
import com.example.cartrack.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Query("SELECT * FROM user WHERE id =:id")
    fun getUserDetail(id: Int?): User

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Delete
    fun deleteUser(deleteUser: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}