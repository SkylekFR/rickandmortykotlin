package com.emilien.rickandmortykotlin.ui.decks

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emilien.rickandmortykotlin.R
import com.emilien.rickandmortykotlin.data.AuthManager.auth
import com.emilien.rickandmortykotlin.data.CardRepository
import com.emilien.rickandmortykotlin.data.database.DatabaseFactory
import com.emilien.rickandmortykotlin.entities.Deck
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.entities.User
import com.emilien.rickandmortykotlin.ui.cards.CardViewModel
import com.emilien.rickandmortykotlin.ui.cards.CardsListActivity
import com.emilien.rickandmortykotlin.ui.cards.DeckAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DecksActivity : AppCompatActivity() {
    lateinit var pokedexBtn : Button
    lateinit var activeDeckLV : RecyclerView
    lateinit var viewModel: DeckViewModel
    lateinit var cardViewModel: CardViewModel
    lateinit var titleTextView : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decks)


        activeDeckLV = findViewById(R.id.activity_decks_activeDeckLV)
        titleTextView = findViewById(R.id.activity_decks_activeDeckTitleTextView)
        DatabaseFactory.initialize(applicationContext)
        viewModel = DeckViewModel()
        cardViewModel = CardViewModel()




        val cardList = ArrayList<Result>()

        val adapter = DeckAdapter()

        cardViewModel.getCards().observe(this, {
            cardList.add(it[0])
            cardList.add(it[1])
            cardList.add(it[2])
            cardList.add(it[3])
            cardList.add(it[4])
            cardList.add(it[5])
            adapter.setData(cardList)
            adapter.notifyDataSetChanged()
        })

        val deck = Deck("First Deck", cardList)


        viewModel.insertUser(User("test", deck))
        titleTextView.setText(deck.name)

        activeDeckLV.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        activeDeckLV.layoutManager = layoutManager
        pokedexBtn = findViewById(R.id.activity_decks_pokedexBtn)

        pokedexBtn.setOnClickListener{
            startActivity( Intent(this, CardsListActivity::class.java))
        }

    }


}