package com.emilien.rickandmortykotlin.ui.cards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.webservices.CardRepository
import kotlin.coroutines.coroutineContext

class CardViewModel : ViewModel() {
    private var cards = MutableLiveData<List<Result>>()

    fun getCards() : MutableLiveData<List<Result>> {
        return cards
    }

    fun loadCardsFromRepository() {
        CardRepository.fetchCardList()
        cards = CardRepository.cardList

    }

    fun getCard(id : Int) : MutableLiveData<Result> {
        val returnedValue = MutableLiveData<Result>()
        returnedValue.value = cards.value?.find {
            it.id == id
        }
        return returnedValue
    }
}