package com.example.cartrack.manager

import io.reactivex.Single

/**
 * Interface for UserManager
 * @author n.quoi
 * @date 10.19.2021
 */

interface UserManager {
    /**
     * This function to do sign in and get back token from Cognito
     * @param id String
     * @param pw password
     * @return Single<String>
     */
    suspend fun signIn(id: String, pw: String, name: String): Single<String>

    /**
     * This has cleared all tokens and this user will have to go through the authentication process to get tokens.
     */
    fun signOut()
}
