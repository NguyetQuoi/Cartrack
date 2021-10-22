package com.example.cartrack.data

import com.example.cartrack.data.model.Account
import com.example.cartrack.data.model.User
import com.example.cartrack.data.model.UserObject
import com.example.cartrack.data.remote.response.BaseResponse
import io.reactivex.Observable
import retrofit2.http.QueryMap

/**
 * Interface for dataSource
 * @author n.quoi
 * @date 10.15.2021
 */

interface DataSource {
    /**
     * Get policy documents: Term And Condition and Policy
     * @return [Observable<List<Document>>]
     */

    fun addUser(user: User): Observable<Boolean>

    fun addUsers(users: List<User>): Observable<Boolean>

    /**
     * Get list observation of a location base on its category name
     * @return Observable<List<User>>
     */
    fun getUsers(): Observable<List<UserObject>>

    suspend fun mockUpAccount(account: Account): Observable<Boolean>

    suspend fun login(username: String, password: String): Observable<Account>
}