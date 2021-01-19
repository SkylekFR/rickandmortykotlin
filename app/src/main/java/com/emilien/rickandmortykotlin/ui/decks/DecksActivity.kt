package com.emilien.rickandmortykotlin.ui.decks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.emilien.rickandmortykotlin.R
import com.emilien.rickandmortykotlin.ui.cards.CardsListActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DecksActivity : AppCompatActivity() {
    lateinit var pokedexBtn : Button
    lateinit var activeDeckLV : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decks)


        pokedexBtn = findViewById(R.id.activity_decks_pokedexBtn)

        pokedexBtn.setOnClickListener{
            startActivity( Intent(this, CardsListActivity::class.java))


        }

    }


}