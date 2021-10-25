package com.example.cartrack.data

import android.content.Context
import com.example.cartrack.data.model.Account
import com.example.cartrack.data.model.User
import com.example.cartrack.data.pref.PreferenceStorage
import io.reactivex.Observable

/**
 * AppDataRepository all repositories of app
 * @author n.quoi
 * @date 10.18.2021
 */

open class AppDataRepository(
    private val context: Context,
    private val remoteDataSource: DataSource,
    private val offlineDataSource: DataSource,
    private val preferenceStorage: PreferenceStorage
) : DataSource {

    override fun addUser(user: User): Observable<Boolean> {
        return offlineDataSource.addUser(user)
    }

    override fun addUsers(users: List<User>): Observable<Boolean> {
        return offlineDataSource.addUsers(users)
    }

    override fun getUsers(): Observable<List<User>> {
        val result = remoteDataSource.getUsers()
        offlineDataSource.addUsers(result.blockingFirst())
        return result
    }

    override suspend fun mockUpAccount(account: Account): Observable<Boolean> {
        return offlineDataSource.mockUpAccount(account)
    }

    override suspend fun login(username: String, password: String): Observable<Boolean> {
        return offlineDataSource.login(username, password)
    }
}
