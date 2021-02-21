package com.emilien.rickandmortykotlin

import android.app.Application
import com.emilien.rickandmortykotlin.data.database.DatabaseFactory

class RickAndMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseFactory.initialize(applicationContext)
    }
}