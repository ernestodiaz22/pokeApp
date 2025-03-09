package com.example.apppokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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
            // Verifica si num_pokedex no es nulo y asigna un valor por defecto si es necesario
            numPokedex.text = "#${pokemon.num_pokedex ?: "Desconocido"}"

            // Verifica si nombrePokemon no es nulo y asigna un valor por defecto si es necesario
            nombrePokemon.text = pokemon.nombrePokemon ?: "Nombre no disponible"

            // Verifica si tipo_1 no es nulo y asigna un valor por defecto si es necesario
            tipo1.text = pokemon.tipo_1 ?: "Tipo 1 desconocido"

            // Si tipo_2 está vacío o es nulo, muestra "N/A"
            tipo2.text = if (pokemon.tipo_2?.isNotEmpty() == true) pokemon.tipo_2 else "N/A"

            // Cargar la imagen desde la URL usando Glide (si la URL de la imagen no es nula)
            Glide.with(itemView.context)
                .load(pokemon.imagen_pokemon) // La URL de la imagen
                .placeholder(R.drawable.electric_icon) // Imagen mientras carga
                .error(R.drawable.alakazam_sprite) // Imagen si hay un error
                .into(imagenPokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_buscador_fav, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size
}
