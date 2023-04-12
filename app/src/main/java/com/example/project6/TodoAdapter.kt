package com.example.project6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodoAdapter (var todos: List<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val poke_name: TextView = itemView.findViewById(R.id.pokeName)
        val poke_weight: TextView = itemView.findViewById(R.id.pokeWeight)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.itemView.findViewById<TextView>(R.id.pokeName).text = todo.name
        holder.itemView.findViewById<TextView>(R.id.pokeWeight).text = todo.weight
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}