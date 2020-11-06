package com.emilien.rickandmortykotlin.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.webservices.NetworkManager

class CardViewModel : ViewModel() {
    private val cards = MutableLiveData<List<Result>>()

    fun getCards() : MutableLiveData<List<Result>> {
        return cards
    }

    fun loadCardsFromRepository() {
       cards =  NetworkManager.getCardList()
    }
}