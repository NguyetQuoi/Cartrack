package com.example.cartrack.data

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

    fun addUser(user: User)

    fun deleteAllUser()

    fun addUsers(users: List<User>)
}