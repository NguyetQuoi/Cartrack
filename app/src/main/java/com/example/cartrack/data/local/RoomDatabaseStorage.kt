package com.example.cartrack.data.local

import com.example.cartrack.data.AppDataRepository
import com.example.cartrack.data.DataSource
import com.example.cartrack.data.local.dao.AccountDao
import com.example.cartrack.data.local.dao.UserDao
import com.example.cartrack.data.model.Account
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
    private val userDao: UserDao,
    private val accountDao: AccountDao
) : DataSource {
    override fun getAllUser(): Observable<List<User>> {
        return Observable.just(userDao.getAllUser())
    }

    override fun addUser(user: User): Observable<Boolean> {
        userDao.addUser(user)
        return Observable.just(true)
    }

    override fun deleteAllUser(): Observable<Boolean> {
        userDao.deleteAll()
        return Observable.just(true)
    }

    override fun addUsers(users: List<User>): Observable<Boolean> {
        for (user in users) {
            userDao.addUser(user)
        }
        return Observable.just(true)
    }

    override suspend fun mockUpAccount(account: Account): Observable<Boolean> {
        return Observable.just(accountDao.addAccount(account).toInt() == 1)
    }

    override suspend fun login(account: Account): Observable<Boolean> {
        return Observable.just(accountDao.getAccount(account.userName, account.password) == 1)
    }
}
