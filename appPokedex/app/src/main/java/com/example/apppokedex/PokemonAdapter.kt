package com.example.apppokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    // ViewHolder para cada ítem del RecyclerView
    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numPokedex: TextView = itemView.findViewById(R.id.num_pokedex)
        val imagenPokemon: ImageView = itemView.findViewById(R.id.imagen_pokemon)
        val nombrePokemon: TextView = itemView.findViewById(R.id.nombrePokemon)
        val tipo1: TextView = itemView.findViewById(R.id.tipo1)
        val tipo2: TextView = itemView.findViewById(R.id.tipo2)

        fun bind(pokemon: Pokemon) {
            numPokedex.text = "#${pokemon.num_pokedex}"
            nombrePokemon.text = pokemon.nombrePokemon
            tipo1.text = pokemon.tipo_1
            tipo2.text = if (pokemon.tipo_2.isNotEmpty()) pokemon.tipo_2 else "N/A"
            imagenPokemon.setImageResource(pokemon.imagen_pokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_buscador_fav, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size
}
