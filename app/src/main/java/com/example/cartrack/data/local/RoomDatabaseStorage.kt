package com.example.cartrack.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cartrack.data.DataSource
import com.example.cartrack.data.local.room.CartrackDatabase
import com.example.cartrack.data.model.User
import com.google.gson.Gson
import io.reactivex.Observable
import org.koin.java.KoinJavaComponent.inject

/**
 * Member of DataSource
 * Stand for Database storage at device
 * @author n.quoi
 * @date 10.18.2021
 */

class RoomDatabaseStorage(val gson: Gson, val database: CartrackDatabase) : DataSource {
    var user: LiveData<User>? = null
    var users: MutableLiveData<List<User>>? = null
    override fun getUsers(): Observable<List<User>> {
        return Observable.just(emptyList())
    }
}
