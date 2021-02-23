package com.emilien.rickandmortykotlin.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.ArrayList

@Entity
class Deck (
    @ColumnInfo(name = "name") val name: String?,
    @Embedded @ColumnInfo(name = "cards") val cards: List<Result>?
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0 // or foodId: Int? = null
}

