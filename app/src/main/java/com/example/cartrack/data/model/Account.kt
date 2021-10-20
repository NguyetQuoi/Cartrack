package com.example.cartrack.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author n.quoi
 * @date 10.20.2021
 */

@Entity(tableName = "account", indices = [Index(value = ["id"], unique = true)])
data class Account(
    @PrimaryKey
    val id: Int,

    @ColumnInfo
    val userName: String,

    @ColumnInfo
    val password: String,

    @ColumnInfo
    val country: String
)