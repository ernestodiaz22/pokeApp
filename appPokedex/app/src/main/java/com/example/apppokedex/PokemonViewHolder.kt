package com.example.apppokedex

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val ivPokemon: ImageView = view.findViewById(R.id.imagen_pokemon)
    private val tvNombrePokemon: TextView = view.findViewById(R.id.nombrePokemon)
    private val tvTipo1Pokemon: TextView = view.findViewById(R.id.tipo1)
    private val tvTipo2Pokemon: TextView = view.findViewById(R.id.tipo2)

    fun render(pokemonItem: Pokemon) {
        tvNombrePokemon.text = pokemonItem.nombrePokemon
        tvTipo1Pokemon.text = if (pokemonItem.tipo_2.isNotEmpty()) {
            "${pokemonItem.tipo_1} / ${pokemonItem.tipo_2}"
        } else {
            pokemonItem.tipo_1
        }
        ivPokemon.setImageResource(pokemonItem.imagen_pokemon)
    }

}

private fun Any.setImageResource(imagenPokemon: String) {
    TODO("Not yet implemented")
}
