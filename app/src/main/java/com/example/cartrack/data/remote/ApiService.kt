package com.example.cartrack.data.remote

import com.example.cartrack.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Interface for api service
 * @author n.quoi
 * @date 10.18.2021
 */

interface ApiService {

    /**
     * Get list of user from server
     * @return [Observable<List<User>>]
     */
    @GET("users")
    fun getUsers(): Observable<List<User>>
}
