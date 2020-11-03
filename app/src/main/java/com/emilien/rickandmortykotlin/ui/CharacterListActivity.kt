package com.emilien.rickandmortykotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emilien.rickandmortykotlin.entities.Example
import com.emilien.rickandmortykotlin.entities.Info
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.R
import com.emilien.rickandmortykotlin.ui.adapters.CharacterListAdapter
import com.emilien.rickandmortykotlin.webservices.NetworkManager
import com.emilien.rickandmortykotlin.webservices.RickAndMortyServices
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
    lateinit var infos: Info
    var page: Int = 1
    lateinit var pageTV: TextView
    lateinit var titleTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)


        service = NetworkManager.rickMortyService
        dataset = mutableListOf()
        recyclerView = findViewById(R.id.character_list_recyclerView)
        pageTV = findViewById<TextView>(R.id.character_list_page_tV)
        titleTV = findViewById<TextView>(R.id.character_list_title)
        myAdapter = CharacterListAdapter(dataset)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        getData()

    }

    fun getData() {
        service.getCharactersList().enqueue(object : Callback<Example> {
            override fun onResponse(
                call: Call<Example>,
                p1: Response<Example>
            ) {
                if (p1.isSuccessful) {
                    dataset.addAll(p1.body().results)
                    infos = p1.body().info
                    pageTV.setText("Page $page")
                    myAdapter.notifyDataSetChanged()
                } else {
                    Log.e(TAG, "onResponse: ${p1.errorBody().string()}")
                    Toast.makeText(baseContext, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<Example>?, throwable: Throwable?) {
                Toast.makeText(baseContext, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getPreviousPage(view: View) {
        if (page > 1) {
            service.getCharacterListFromPage(--page).enqueue(object : Callback<Example> {
                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    if (response.isSuccessful) {
                        dataset.clear()
                        dataset.addAll(response.body().results)
                        pageTV.setText("Page $page")
                        myAdapter.notifyDataSetChanged()
                    }
                    else {
                        Toast.makeText(baseContext, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Example>, throwable: Throwable) {
                    Toast.makeText(baseContext, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    companion object {
        private const val TAG = "CharacterListActivity"
    }



    fun getNextPage(view: View) {
        if (page < infos.pages){
            service.getCharacterListFromPage(++page).enqueue(object : Callback<Example> {
                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    if (response.isSuccessful()) {
                        dataset.clear()
                        dataset.addAll(response.body().results)
                        pageTV.setText("Page $page")
                        myAdapter.notifyDataSetChanged()
                    }
                    else {
                        Toast.makeText(baseContext, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Example>, throwable: Throwable) {
                    Toast.makeText(baseContext, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun close(view: View) {
        finish()
    }




}