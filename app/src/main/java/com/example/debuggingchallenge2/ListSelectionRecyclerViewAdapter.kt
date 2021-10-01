package com.example.debuggingchallenge2

import ListSelectionViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(private val stateAndCapitals: ArrayList<CountryAndCapital>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position+1).toString()
        holder.listTitle.text = stateAndCapitals[position].country +"\\" +stateAndCapitals[position].capital
    }

    override fun getItemCount(): Int {
        return stateAndCapitals.size
    }
}