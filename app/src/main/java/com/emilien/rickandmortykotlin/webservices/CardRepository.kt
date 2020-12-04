package com.emilien.rickandmortykotlin.webservices

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emilien.rickandmortykotlin.entities.Example
import com.emilien.rickandmortykotlin.entities.Info
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.ui.cards.CardsListActivity
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

object CardRepository {
    //Creating Auth Interceptor to add api_key query in front of all the requests.
    val factory = ApiFactory.rickMortyService

    suspend fun fetchCardList() = factory.getCharactersList().results

    suspend fun fetchCardListFromPage(page: Int) = factory.getCharacterListFromPage(page)

    suspend fun fetchCharacterFromId(id: Int) = factory.getCharacterFromId(id)
}