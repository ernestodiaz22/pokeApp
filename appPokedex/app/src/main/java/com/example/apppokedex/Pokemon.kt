    package com.example.apppokedex

    import com.google.gson.annotations.SerializedName

    data class Pokemon(
        @SerializedName("numero_pokedex") val num_pokedex: Int,
        @SerializedName("imagen") val imagen_pokemon: String,
        @SerializedName("nombre") val nombrePokemon: String,
        @SerializedName("tipo1") val tipo_1: String,
        @SerializedName("tipo2") val tipo_2: String?
    )
