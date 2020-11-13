package com.emilien.rickandmortykotlin.ui.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.R
import com.squareup.picasso.Picasso

class CardsListAdapter(val viewModel: CardViewModel) :
    RecyclerView.Adapter<CardsListAdapter.CharacterHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.

    private var myDataset = emptyList<Result>()

    class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String, image: String) {
            itemView.findViewById<TextView>(R.id.character_list_adapter_title).text =
                text
            Picasso.get().load(image)
                .into(itemView.findViewById<ImageView>(R.id.character_list_adapter_icon))
        }

        companion object {
            fun newInstance(parent: ViewGroup) =
                CharacterHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_character_list, parent, false)
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder =
        CharacterHolder.newInstance(
            parent
        )


    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(myDataset[position].name, myDataset[position].image)
        holder.itemView.setOnClickListener {
            val transaction =
                (it.context as CardsListActivity).supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
                .replace(R.id.container, CardFragment.newInstance(myDataset[position].id))
                .addToBackStack("DetailedCharacter")
                .commit()
        }

    }

    fun setData(listCard : List<Result>) {
        myDataset = listCard
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = myDataset.size
}










