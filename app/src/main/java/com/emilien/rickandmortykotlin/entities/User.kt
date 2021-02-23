package com.emilien.rickandmortykotlin.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "active_deck") val activeDeck: Deck?
)
{
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}