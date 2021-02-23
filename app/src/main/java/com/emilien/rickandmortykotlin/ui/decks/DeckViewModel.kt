package com.emilien.rickandmortykotlin.ui.decks

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emilien.rickandmortykotlin.data.CardRepository
import com.emilien.rickandmortykotlin.data.database.DatabaseFactory
import com.emilien.rickandmortykotlin.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeckViewModel : ViewModel() {
    val database = DatabaseFactory.userDatabase

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().insertAll(user)
        }

    }

}