package com.emilien.rickandmortykotlin.ui.cards

import androidx.lifecycle.*
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.data.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CardViewModel : ViewModel() {
    private var cards = MutableLiveData<List<Result>>()
    private var showedCard = MutableLiveData<Result>();

    fun getCards() : MutableLiveData<List<Result>> {
        if(cards.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                cards.postValue(CardRepository.fetchCardListFromPage(1))
            }
        }
        return cards
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