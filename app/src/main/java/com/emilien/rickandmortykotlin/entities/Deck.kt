package com.emilien.rickandmortykotlin.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Deck (
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @Embedded @ColumnInfo(name = "cards") val cards: List<Result>?
)
