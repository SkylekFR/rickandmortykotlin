package com.emilien.rickandmortykotlin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emilien.rickandmortykotlin.entities.User
import com.emilien.rickandmortykotlin.entities.converters.Converter


@Database(entities = arrayOf(User::class), version = 4)
@TypeConverters(Converter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}