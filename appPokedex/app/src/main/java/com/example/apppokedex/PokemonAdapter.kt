package com.example.apppokedex

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class PokemonAdapter(
    private var pokemonList: MutableList<Pokemon>,
    private val onItemClickListener: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val numPokedex: TextView = itemView.findViewById(R.id.num_pokedex)
        private val imagenPokemon: ImageView = itemView.findViewById(R.id.imagen_pokemon)
        private val nombrePokemon: TextView = itemView.findViewById(R.id.nombrePokemon)
        private val tipo1: TextView = itemView.findViewById(R.id.tipo1)
        private val tipo2: TextView = itemView.findViewById(R.id.tipo2)
        private val contenedorPrimario : View = itemView.findViewById(R.id.contenedorPrimario)
        fun cambiarColorTipo(tipoTextView: TextView, tipo: String){
            when(tipo.lowercase()){
                "acero"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#60a1b8"))
                }
                "agua"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#2481f0"))
                }
                "bicho"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#91a119"))
                }
                "dragon"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#5061e1"))
                }
                "eléctrico"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#fac000"))
                }
                "fantasma"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#704170"))
                }
                "fuego"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#e62829"))
                }
                "hada"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#ef71ef"))
                }
                "hielo"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#3fd8ff"))
                }
                "lucha"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#ff8000"))
                }
                "normal"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#9fa19f"))
                }
                "planta"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#3fa129"))
                }
                "psíquico"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#ef4179"))
                }
                "roca"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#915121"))
                }
                "siniestro"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#50413f"))
                }
                "tierra"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#92501b"))
                }
                "veneno"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#8f41cb"))
                }
                "volador"->{
                    tipoTextView.setBackgroundColor(Color.parseColor("#81b9ef"))
                }
            }
        }

        fun bind(pokemon: Pokemon, onItemClickListener: (Pokemon) -> Unit) {
            numPokedex.text = "#${pokemon.num_pokedex ?: "Desconocido"}"
            nombrePokemon.text = pokemon.nombrePokemon ?: "Nombre no disponible"
            tipo1.text = pokemon.tipo_1.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString()  } ?: "Tipo 1 desconocido"
            cambiarColorTipo(tipo1, pokemon.tipo_1)
            if (!pokemon.tipo_2.isNullOrEmpty() && pokemon.tipo_2.lowercase() != "none") {
                tipo2.visibility = View.VISIBLE
                tipo2.text = pokemon.tipo_2.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString()  }
                cambiarColorTipo(tipo2, pokemon.tipo_2)
            } else {
                tipo2.visibility = View.GONE
            }

            Glide.with(itemView.context)
                .load(pokemon.imagen_pokemon)
                .placeholder(R.drawable.electric_icon)
                .error(R.drawable.alakazam_sprite)
                .into(imagenPokemon)

            Glide.with(itemView.context)
                .asBitmap()
                .load(pokemon.imagen_pokemon)
                .placeholder(R.drawable.electric_icon)
                .error(R.drawable.alakazam_sprite)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val palette = Palette.from(resource).generate()


                        val dominantColor = palette.getDominantColor(Color.BLACK)


                        contenedorPrimario.setBackgroundColor(dominantColor)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        contenedorPrimario.setBackgroundColor(Color.WHITE)
                    }
                })


            itemView.setOnClickListener { onItemClickListener(pokemon) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_buscador_fav, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon, onItemClickListener)
    }

    override fun getItemCount(): Int = pokemonList.size

    // Método para eliminar un Pokémon de la lista
    fun removePokemon(pokemon: Pokemon) {
        val position = pokemonList.indexOf(pokemon)
        if (position != -1) {
            pokemonList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    // Método para actualizar la lista de Pokémon
    fun updatePokemonList(newList: List<Pokemon>) {
        pokemonList.clear()
        pokemonList.addAll(newList)
        notifyDataSetChanged()
    }
}