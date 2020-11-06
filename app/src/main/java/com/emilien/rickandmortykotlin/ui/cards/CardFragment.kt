package com.emilien.rickandmortykotlin.ui.cards

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.R
import com.emilien.rickandmortykotlin.webservices.NetworkManager
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterDetailFragment() : Fragment() {
    lateinit var nameTextView: TextView
    lateinit var speciesTextView: TextView
    lateinit var typeTextView: TextView
    lateinit var genderTextView: TextView
    lateinit var iconImageView: ImageView
    lateinit var popFragmentButton: Button
    val networkManager = NetworkManager.rickMortyService
    var characterId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val id  = arguments?.getInt("CharacterID")
        Log.i(TAG, "onAttach: id = $id")
    }

    companion object {

        @JvmStatic
        fun newInstance(id: Int) = CharacterDetailFragment()
            .apply {
            this.arguments = Bundle().apply {
                this.putInt("CharacterID", id)
            }
        }
        private const val TAG = "CharacterListActivity"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterId = arguments?.getInt("CharacterID") ?: throw IllegalStateException("No ID found")
        nameTextView = view.findViewById(R.id.fragment_character_detail_nameTV)
        speciesTextView = view.findViewById(R.id.fragment_character_detail_speciesTV)
        genderTextView = view.findViewById(R.id.fragment_character_detail_genderTextView)
        typeTextView = view.findViewById(R.id.fragment_character_detail_typeTextView)
        iconImageView = view.findViewById(R.id.fragment_character_detail_iconIV)
        popFragmentButton = view.findViewById(R.id.fragment_character_detail_popFragmentButton)
        popFragmentButton.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        getData()
    }

    fun getData() {
        networkManager.getCharacterFromId(characterId).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {
                if (response?.isSuccessful == true) {
                    nameTextView.text = response.body().name
                    speciesTextView.text = response.body().species
                    genderTextView.text = response.body().gender
                    if(!response.body().type.equals("")) {
                        typeTextView.text = response.body().type
                    }else {
                        typeTextView.text = "Unknown type"
                    }

                    Picasso.get().load(response.body().image).into(iconImageView)
                }
                else {
                    Toast.makeText(context, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<Result>?, p1: Throwable?) {
                Toast.makeText(context, "Cannot retrieve data", Toast.LENGTH_SHORT).show()
            }

        })


    }




}