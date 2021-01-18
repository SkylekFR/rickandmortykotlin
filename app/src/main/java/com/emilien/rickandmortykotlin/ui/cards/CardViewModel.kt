package com.emilien.rickandmortykotlin.ui.cards

import androidx.lifecycle.*
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.webservices.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class CardViewModel : ViewModel() {
    private var cards = MutableLiveData<List<Result>>()

    private var showedCard = MutableLiveData<Result>();

    fun getCards() : MutableLiveData<List<Result>> {
        return cards
    }

    fun loadCardsFromRepository()  {
        viewModelScope.launch(Dispatchers.IO) {
            cards.postValue(CardRepository.fetchCardListFromPage(1))
        }

    }


    fun fetchCardById(id: Int) : MutableLiveData<Result> {
        val rValue = MutableLiveData<Result>()
        viewModelScope.launch(Dispatchers.IO) {
            rValue.postValue(CardRepository.fetchCharacterFromId(id))
        }
        return rValue
    }

    fun getCard(id : Int) : MutableLiveData<Result> {
        val returnedValue = MutableLiveData<Result>()
        returnedValue.value = cards.value?.find {
            it.id === id
        }
        return returnedValue
    }
}