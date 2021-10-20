package com.example.cartrack.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author n.quoi
 * @date 10.18.2021
 */

@Entity
data class Geo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo
    val lat: Double,

    @ColumnInfo
    val lng: Double
)