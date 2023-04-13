package com.example.project6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class TodoAdapter (val pokeList: MutableList<Pokemon>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    data class Pokemon(val pokeImage: String, val pokeName: String, val pokeWeight: String)
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val pokeName: TextView = itemView.findViewById(R.id.pokeName)
        val pokeWeight: TextView = itemView.findViewById(R.id.pokeWeight)
        val pokeImage: ImageView = itemView.findViewById(R.id.pokeImage)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = pokeList[position]
        Glide.with(holder.itemView)
            .load(todo.pokeImage)
            .centerCrop()
            .into(holder.pokeImage)
        holder.pokeName.text = todo.pokeName
        holder.pokeWeight.text = todo.pokeWeight
    }

    override fun getItemCount(): Int {
        return pokeList.size
    }
}