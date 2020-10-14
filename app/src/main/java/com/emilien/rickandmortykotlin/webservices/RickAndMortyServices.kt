package com.emilien.rickandmortykotlin.webservices

import com.emilien.rickandmortykotlin.entities.Example
import com.emilien.rickandmortykotlin.entities.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickAndMortyServices {
    @GET("character")
    fun getCharactersList(): Call<Example>

    @GET("character")
    fun getCharacterListFromPage(@Query("page") page: Int): Call<Example>

    @GET("character/{id}")
    fun getCharacterFromId(@Path("id") id: Int): Call<Result>
}