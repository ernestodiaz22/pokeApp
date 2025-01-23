package com.example.apppokedex

class PokemonProvider {
    companion object {
        val pokemonList: List<Pokemon> = listOf(
            Pokemon(
                num_pokedex = 1,
                imagen_pokemon = R.drawable.bulbasaur,
                nombrePokemon = "Bulbasaur",
                tipo_1 = "Planta",
                tipo_2 = "Veneno"
            ),
            Pokemon(
                num_pokedex = 4,
                imagen_pokemon = R.drawable.charmander,
                nombrePokemon = "Charmander",
                tipo_1 = "Fuego",
                tipo_2 = ""
            ),
            Pokemon(
                num_pokedex = 7,
                imagen_pokemon = R.drawable.squirtle,
                nombrePokemon = "Squirtle",
                tipo_1 = "Agua",
                tipo_2 = ""
            )
        )
    }
}
