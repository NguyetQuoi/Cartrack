package com.example.cartrack.data

import android.content.Context
import com.example.cartrack.data.model.User
import com.example.cartrack.data.pref.PreferenceStorage
import io.reactivex.Observable

/**
 * AppDataRepository all repositories of app
 * @author n.quoi
 * @date 10.18.2021
 */

open class AppDataRepository(
    private val remoteDataSource: DataSource, private val offlineDataSource: DataSource
) : DataSource {

    override fun getAllUser(): Observable<List<User>> {
        return remoteDataSource.getAllUser()
        //return offlineDataSource.getAllUser()
    }

    override fun addUser(user: User) {
        offlineDataSource.addUser(user)
    }

    override fun deleteAllUser() {
        offlineDataSource.deleteAllUser()
    }

    override fun addUsers(users: List<User>) {
        offlineDataSource.addUsers(users)
    }
}
