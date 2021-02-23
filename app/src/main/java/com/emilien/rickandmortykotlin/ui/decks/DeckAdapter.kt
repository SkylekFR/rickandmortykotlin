package com.emilien.rickandmortykotlin.ui.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emilien.rickandmortykotlin.entities.Result
import com.emilien.rickandmortykotlin.R
import com.emilien.rickandmortykotlin.entities.Deck

class DeckAdapter() :
    RecyclerView.Adapter<DeckAdapter.DeckHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.

    private var myDataset = emptyList<Result>()

    class DeckHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Result) {
            itemView.findViewById<TextView>(R.id.adapter_deck_cardName).text =
                card.name

                        Glide.with(itemView).load(card.image).circleCrop()
                .into(itemView.findViewById<ImageView>(R.id.adapter_deck_imageView))
        }

        companion object {
            fun newInstance(parent: ViewGroup) =
                DeckHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_deck, parent, false)
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckHolder =
        DeckHolder.newInstance(
            parent
        )


    override fun onBindViewHolder(holder: DeckHolder, position: Int) {
        holder.bind(myDataset[position])

    }

    fun setData(listCard : List<Result>) {
        myDataset = listCard
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = myDataset.size
}










