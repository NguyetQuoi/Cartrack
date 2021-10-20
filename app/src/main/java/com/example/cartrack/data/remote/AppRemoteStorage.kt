package com.example.cartrack.data.remote

import com.example.cartrack.data.DataSource
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

    override fun addUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun deleteAllUser() {
        TODO("Not yet implemented")
    }

    override fun addUsers(users: List<User>) {
        TODO("Not yet implemented")
    }
}
