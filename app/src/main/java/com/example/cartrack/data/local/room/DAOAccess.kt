package com.example.cartrack.data.local.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cartrack.data.model.User

@Dao
interface DAOAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(user: User)

    @Query("SELECT * FROM User WHERE id =:id")
    fun getUserDetail(id: Int?): LiveData<User>

    @Query("SELECT * FROM User")
    fun getAllUser(): MutableLiveData<List<User>>
}