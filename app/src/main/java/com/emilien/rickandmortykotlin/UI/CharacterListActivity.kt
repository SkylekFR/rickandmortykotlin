package com.emilien.rickandmortykotlin.UI

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emilien.rickandmortykotlin.Entity.Example
import com.emilien.rickandmortykotlin.Entity.Result
import com.emilien.rickandmortykotlin.R
import com.emilien.rickandmortykotlin.UI.Adapters.CharacterListAdapter
import com.emilien.rickandmortykotlin.WebServices.RickAndMortyServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterListActivity : AppCompatActivity() {
    lateinit var service: RickAndMortyServices
    lateinit var dataset: MutableList<Result>
    lateinit var myAdapter: CharacterListAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<RickAndMortyServices>(RickAndMortyServices::class.java)
        dataset = mutableListOf()
        recyclerView = findViewById(R.id.character_list_recyclerView)
        myAdapter = CharacterListAdapter(dataset)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun getData() {
        service.getCharactersList().enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if (response.isSuccessful()) {
                    dataset.addAll(response.body().results)
                    //pageTV.setText("Page $page")
                    myAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Example>, throwable: Throwable) {}
        })
    }

//    fun goToNextPage(view: View?) {
//        if (page < info.getPages()) {
//            service.characterList(++page).enqueue(object : Callback<Example> {
//                override fun onResponse(call: Call<Example>, response: Response<Example>) {
//                    if (response.isSuccessful()) {
//                        dataset.clear()
//                        dataset.addAll(response.body().getResults())
//                        pageTV.setText("Page $page")
//                        characterListAdapter.notifyDataSetChanged()
//                    }
//                }
//
//                override fun onFailure(call: Call<Example>, throwable: Throwable) {}
//            })
//        }
//    }
//
//    fun goToPreviousPage(view: View?) {
//        if (page > 1) {
//            service.characterList(--page).enqueue(object : Callback<Example> {
//                override fun onResponse(call: Call<Example>, response: Response<Example>) {
//                    if (response.isSuccessful()) {
//                        dataset.clear()
//                        dataset.addAll(response.body().getResults())
//                        pageTV.setText("Page $page")
//                        characterListAdapter.notifyDataSetChanged()
//                    }
//                }
//
//                override fun onFailure(call: Call<Example>, throwable: Throwable) {}
//            })
//        }
//    }
}