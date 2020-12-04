package com.emilien.rickandmortykotlin.webservices

import com.emilien.rickandmortykotlin.entities.Example
import com.emilien.rickandmortykotlin.entities.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickAndMortyServices {
    @GET("character")
    suspend fun getCharactersList(): Example

    @GET("character")
    suspend fun getCharacterListFromPage(@Query("page") page: Int): Example

    @GET("character/{id}")
    suspend fun getCharacterFromId(@Path("id") id: Int): Result
}