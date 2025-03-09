package com.example.apppokedex

import retrofit2.Call
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemons")
    fun getPokemonList(): Call<List<Pokemon>>
}
