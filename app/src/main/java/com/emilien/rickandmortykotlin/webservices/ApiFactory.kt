package com.emilien.rickandmortykotlin.webservices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {


    private val baseURL = "https://rickandmortyapi.com/api/"

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val rickMortyService: RickAndMortyServices = retrofit().create(RickAndMortyServices::class.java)
}