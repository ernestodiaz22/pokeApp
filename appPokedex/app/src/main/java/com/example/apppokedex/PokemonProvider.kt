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
                num_pokedex = 2,
                imagen_pokemon = R.drawable.ivysaur,
                nombrePokemon = "Ivysaur",
                tipo_1 = "Planta",
                tipo_2 = "Veneno"
            ),
            Pokemon(
                num_pokedex = 3,
                imagen_pokemon = R.drawable.venusaur,
                nombrePokemon = "Venusaur",
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
                num_pokedex = 5,
                imagen_pokemon = R.drawable.charmeleon,
                nombrePokemon = "Charmeleon",
                tipo_1 = "Fuego",
                tipo_2 = ""
            ),
            Pokemon(
                num_pokedex = 6,
                imagen_pokemon = R.drawable.charizard,
                nombrePokemon = "Charizard",
                tipo_1 = "Fuego",
                tipo_2 = "Volador"
            ),
            Pokemon(
                num_pokedex = 7,
                imagen_pokemon = R.drawable.squirtle,
                nombrePokemon = "Squirtle",
                tipo_1 = "Agua",
                tipo_2 = ""
            ),
            Pokemon(
                num_pokedex = 8,
                imagen_pokemon = R.drawable.wartortle,
                nombrePokemon = "Wartortle",
                tipo_1 = "Agua",
                tipo_2 = ""
            ),
            Pokemon(
                num_pokedex = 9,
                imagen_pokemon = R.drawable.blastoise,
                nombrePokemon = "Blastoise",
                tipo_1 = "Agua",
                tipo_2 = ""
            ),
            Pokemon(
                num_pokedex = 10,
                imagen_pokemon = R.drawable.caterpie,
                nombrePokemon = "Caterpie",
                tipo_1 = "Bicho",
                tipo_2 = ""
            ),
            Pokemon(
                num_pokedex = 25,
                imagen_pokemon = R.drawable.caterpie,
                nombrePokemon = "Caterpie",
                tipo_1 = "Bicho",
                tipo_2 = ""
            )
        )
    }
}

