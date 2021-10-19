package com.example.cartrack.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author n.quoi
 * @date 10.18.2021
 */
@Entity (tableName = "User")
data class User(
    @PrimaryKey
    val id: Int,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val useName: String,

    @ColumnInfo
    val email: String,

    @ColumnInfo
    val address: String,

    @ColumnInfo
    val street: String,

    @ColumnInfo
    val suite: String,

    @ColumnInfo
    val city: String,

    @ColumnInfo
    val zipCode: String,

    @ColumnInfo
    val geo: Geo,

    @ColumnInfo
    val phone: String,

    @ColumnInfo
    val website: String,

    @ColumnInfo
    val company: Company
)