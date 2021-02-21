package com.emilien.rickandmortykotlin.data.database

import android.content.Context
import androidx.room.Room

object DatabaseFactory {

    lateinit var userDatabase: UserDatabase

    fun initialize(appContext : Context) {
        userDatabase = Room.databaseBuilder(appContext, UserDatabase::class.java, "userDB")
            .fallbackToDestructiveMigration()
            //.allowMainThreadQueries()
            .build()
    }
}