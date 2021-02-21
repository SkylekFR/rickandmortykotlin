package com.emilien.rickandmortykotlin.entities.converters

import androidx.room.TypeConverter
import com.emilien.rickandmortykotlin.entities.Deck
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun toDeck(deck: String) : Deck {
        return Gson().fromJson(deck, Deck::class.java);
    }

    @TypeConverter
    fun fromDeck(deck: Deck) : String {
        val gson = Gson()
        return gson.toJson(deck)
    }
}