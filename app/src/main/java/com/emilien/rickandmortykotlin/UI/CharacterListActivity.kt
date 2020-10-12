package com.emilien.rickandmortykotlin.UI

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.emilien.rickandmortykotlin.Entity.Example
import com.emilien.rickandmortykotlin.Entity.Result
import com.emilien.rickandmortykotlin.R
import com.emilien.rickandmortykotlin.WebServices.RickAndMortyServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterListActivity : AppCompatActivity() {
    lateinit var service: RickAndMortyServices
    lateinit var dataset: List<Result>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<RickAndMortyServices>(RickAndMortyServices::class.java)
        dataset = List<Result>()
    }

    fun getData() {
        service.getCharactersList(1).enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if (response.isSuccessful()) {
                    dataset.addAll(response.body().getResults())
                    info = response.body().getInfo()
                    pageTV.setText("Page $page")
                    characterListAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Example>, throwable: Throwable) {}
        })
    }

    fun goToNextPage(view: View?) {
        if (page < info.getPages()) {
            service.characterList(++page).enqueue(object : Callback<Example> {
                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    if (response.isSuccessful()) {
                        dataset.clear()
                        dataset.addAll(response.body().getResults())
                        pageTV.setText("Page $page")
                        characterListAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<Example>, throwable: Throwable) {}
            })
        }
    }

    fun goToPreviousPage(view: View?) {
        if (page > 1) {
            service.characterList(--page).enqueue(object : Callback<Example> {
                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    if (response.isSuccessful()) {
                        dataset.clear()
                        dataset.addAll(response.body().getResults())
                        pageTV.setText("Page $page")
                        characterListAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<Example>, throwable: Throwable) {}
            })
        }
    }
}