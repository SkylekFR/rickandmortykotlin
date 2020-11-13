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

    val cardList = MutableLiveData<List<Result>>()
    val error = MutableLiveData<String>()


   fun fetchCardList() {
       factory.getCharactersList().enqueue(object : Callback<Example> {
            override fun onResponse(
                call: Call<Example>,
                p1: Response<Example>
            ) {
                if (p1.isSuccessful) {
                   cardList.value = p1.body().results
                } else {
                    Log.e("NetworkManager", "onResponse: ${p1.errorBody().string()}")
                    //Toast.makeText(, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<Example>?, throwable: Throwable?) {
                //Toast.makeText(baseContext, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
            }
        })


    }
}