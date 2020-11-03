package com.emilien.rickandmortykotlin.webservices

import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val baseURL = "https://api-rickandmorty-tcg.herokuapp.com/"
    
    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val rickMortyService: RickAndMortyServices = retrofit().create(RickAndMortyServices::class.java)
}