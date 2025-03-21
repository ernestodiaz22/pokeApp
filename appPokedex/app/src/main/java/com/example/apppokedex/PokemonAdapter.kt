package com.example.apppokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.appcompat.app.AlertDialog

class PokemonAdapter(
    private var pokemonList: List<Pokemon>,
    private val onItemClickListener: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numPokedex: TextView = itemView.findViewById(R.id.num_pokedex)
        val imagenPokemon: ImageView = itemView.findViewById(R.id.imagen_pokemon)
        val nombrePokemon: TextView = itemView.findViewById(R.id.nombrePokemon)
        val tipo1: TextView = itemView.findViewById(R.id.tipo1)
        val tipo2: TextView = itemView.findViewById(R.id.tipo2)


        fun bind(pokemon: Pokemon, onItemClickListener: (Pokemon) -> Unit) {
            numPokedex.text = "#${pokemon.num_pokedex ?: "Desconocido"}"
            nombrePokemon.text = pokemon.nombrePokemon ?: "Nombre no disponible"
            tipo1.text = pokemon.tipo_1 ?: "Tipo 1 desconocido"
            tipo2.text = if (pokemon.tipo_2?.isNotEmpty() == true) pokemon.tipo_2 else "N/A"

            Glide.with(itemView.context)
                .load(pokemon.imagen_pokemon)
                .placeholder(R.drawable.electric_icon)
                .error(R.drawable.alakazam_sprite)
                .into(imagenPokemon)

            itemView.setOnClickListener {
                onItemClickListener(pokemon)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_buscador_fav, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position], onItemClickListener)
    }

    override fun getItemCount(): Int = pokemonList.size

    fun removePokemon(pokemon: Pokemon) {
        val position = pokemonList.indexOf(pokemon)
        if (position != -1) {
            (pokemonList as MutableList).removeAt(position)
            notifyItemRemoved(position)
        }
    }
    fun updatePokemonList(newList: List<Pokemon>) {
        pokemonList = newList
        notifyDataSetChanged()
    }
}
