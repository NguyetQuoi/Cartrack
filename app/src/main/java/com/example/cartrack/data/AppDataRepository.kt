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

    override fun getAllUser(): Observable<List<User>> {
        return remoteDataSource.getAllUser().doOnNext {
            offlineDataSource.addUsers(it)
        }
    }

    override fun addUser(user: User): Observable<Boolean> {
        return offlineDataSource.addUser(user)
    }

    override fun deleteAllUser(): Observable<Boolean> {
        return offlineDataSource.deleteAllUser()
    }

    override fun addUsers(users: List<User>): Observable<Boolean> {
        return offlineDataSource.addUsers(users)
    }

    override suspend fun mockUpAccount(account: Account): Observable<Boolean> {
        return offlineDataSource.mockUpAccount(account)
    }

    override suspend fun login(account: Account): Observable<Boolean> {
        return offlineDataSource.login(account)
    }
}
