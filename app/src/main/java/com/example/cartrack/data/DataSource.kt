package com.example.cartrack.data

import com.example.cartrack.data.model.Account
import com.example.cartrack.data.model.User
import io.reactivex.Observable

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
    fun getAllUser(): Observable<List<User>>

    fun addUser(user: User): Observable<Boolean>

    fun deleteAllUser(): Observable<Boolean>

    fun addUsers(users: List<User>): Observable<Boolean>

    suspend fun mockUpAccount(account: Account): Observable<Boolean>

    suspend fun login(account: Account): Observable<Boolean>
}