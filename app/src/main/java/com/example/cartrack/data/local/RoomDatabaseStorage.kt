package com.example.cartrack.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.cartrack.data.DataSource
import com.example.cartrack.data.model.User
import com.google.gson.Gson
import io.reactivex.Observable

/**
 * Member of DataSource
 * Stand for Database storage at device
 * @author n.quoi
 * @date 10.18.2021
 */

class RoomDatabaseStorage(
    val gson: Gson,
    val provideDatabase: CartrackDatabase,
    private val userDao: UserDao
) : DataSource {
    override fun getAllUser(): Observable<List<User>> {
        return Observable.just(userDao.getAllUser())
    }

    override fun addUser(user: User) {
        userDao.addUser(user)
    }

    override fun deleteAllUser() {
        userDao.deleteAll()
    }

    override fun addUsers(users: List<User>) {
        for (user in users) {
            userDao.addUser(user)
        }
    }
}
