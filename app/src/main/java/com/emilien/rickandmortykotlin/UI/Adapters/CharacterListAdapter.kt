package com.emilien.rickandmortykotlin.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emilien.rickandmortykotlin.Entity.Example
import com.emilien.rickandmortykotlin.Entity.Result
import com.emilien.rickandmortykotlin.R
import com.squareup.picasso.Picasso

class CharacterListAdapter(private val myDataset: MutableList<Result>) :
    RecyclerView.Adapter<CharacterListAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.



    class MyViewHolder constructor(view: View) : RecyclerView.ViewHolder(view){
        var titleTV: TextView
        var iconIV: ImageView

        init {
            this.titleTV = view.findViewById(R.id.character_list_adapter_title)
            this.iconIV = view.findViewById(R.id.character_list_adapter_icon)
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterListAdapter.MyViewHolder {
        // create a new view
        val myView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_character_list, parent, false) as View
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(myView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.titleTV.text = myDataset[position].name
        Picasso.get().load(myDataset.get(position).image).into(holder.iconIV)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}