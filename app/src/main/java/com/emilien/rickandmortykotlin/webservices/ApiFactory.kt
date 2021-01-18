package com.emilien.rickandmortykotlin.webservices

import com.emilien.rickandmortykotlin.BuildConfig
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {


    private val baseURL =  BuildConfig.BackOfficeURL


    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val rickMortyService: RickAndMortyServices = retrofit().create(RickAndMortyServices::class.java)
}