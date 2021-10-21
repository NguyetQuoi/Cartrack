package com.example.cartrack.data.remote

import com.example.cartrack.data.DataSource
import com.example.cartrack.data.model.Account
import com.example.cartrack.data.model.User
import com.example.cartrack.data.remote.ApiService
import io.reactivex.Observable
import retrofit2.Retrofit

/**
 * An app storage remote
 * @author n.quoi
 * @date 10.18.2021
 */

class AppRemoteStorage(retrofit: Retrofit) : DataSource {
    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    override fun getAllUser(): Observable<List<User>> {
        return apiService.getUser().map { response -> response.data }
    }

    override fun addUser(user: User): Observable<Boolean> {
        return Observable.just(true)
    }

    override fun deleteAllUser() : Observable<Boolean>{
        return Observable.just(true)
    }

    override fun addUsers(users: List<User>): Observable<Boolean> {
        return Observable.just(true)
    }

    override suspend fun mockUpAccount(account: Account) : Observable<Boolean>{
        return Observable.just(true)
    }

    override suspend fun login(username: String, password: String): Observable<Boolean> {
        return Observable.just(true)
    }
}
