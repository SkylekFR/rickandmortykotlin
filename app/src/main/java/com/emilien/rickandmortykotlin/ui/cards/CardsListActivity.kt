package com.emilien.rickandmortykotlin.ui.cards

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emilien.rickandmortykotlin.entities.Example
import com.emilien.rickandmortykotlin.entities.Info
import com.emilien.rickandmortykotlin.R


class CardsListActivity : AppCompatActivity() {
    var myAdapter = CardsListAdapter()
    lateinit var recyclerView: RecyclerView
    lateinit var infos: Info
    var page: Int = 1
    lateinit var pageTV: TextView
    lateinit var titleTV: TextView
    private lateinit var viewModel: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        viewModel = ViewModelProvider(this).get(CardViewModel::class.java)
        recyclerView = findViewById(R.id.character_list_recyclerView)
        pageTV = findViewById(R.id.character_list_page_tV)
        titleTV = findViewById(R.id.character_list_title)
        viewModel.loadCardsFromRepository()
        viewModel.getCards().observe(this, Observer {
            myAdapter.setData(it)
        })
        myAdapter = CardsListAdapter()
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }


/*
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
    }*/

    companion object {
        private const val TAG = "CharacterListActivity"
    }

    /*private val changeObserver = Observer<Example> { value ->
        value?.let { txt_fragment.text = it }
    }*/


    /*fun getNextPage(view: View) {
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
    }*/

    fun close(view: View) {
        finish()
    }




}