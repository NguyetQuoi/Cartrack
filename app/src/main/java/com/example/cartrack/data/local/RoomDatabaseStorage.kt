package com.example.cartrack.data.local

import com.example.cartrack.data.DataSource
import com.example.cartrack.data.model.User
import com.google.gson.Gson
import io.reactivex.Observable

/**
 * Member of DataSource
 * Stand for Database storage at device
 * @author n.quoi
 * @date 10.18.2021
 */

class RoomDatabaseStorage(val gson: Gson) : DataSource {
    override fun getUsers(): Observable<List<User>> {
        return Observable.just(emptyList())
    }
}
