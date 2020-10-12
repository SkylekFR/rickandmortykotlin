package com.emilien.rickandmortykotlin.WebServices

import retrofit2.http.GET
import com.emilien.rickandmortykotlin.Entity.Example
import retrofit2.Call


interface RickAndMortyServices {
    @GET("characters")
    fun getCharactersList(): Call<Example>
}