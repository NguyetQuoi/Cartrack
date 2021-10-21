package com.example.cartrack.data.local

import android.util.JsonReader
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.example.cartrack.data.model.User
import java.io.InputStream

object DataParser {
    @Throws(JsonIOException::class, JsonSyntaxException::class)
    fun parseObservationsData(unprocessedSessionData: InputStream, gson: Gson): List<User> {
        val jsonReader = JsonReader(unprocessedSessionData.reader())
        val type = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(unprocessedSessionData.reader(), type)
    }
}