package com.emilien.rickandmortykotlin.data

import com.emilien.rickandmortykotlin.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {


    private val baseURL =  BuildConfig.BackOfficeURL


    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cardService: CardServices = retrofit().create(CardServices::class.java)
}