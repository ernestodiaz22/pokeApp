package com.example.apppokedex

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemons")
    fun getPokemonList(): Call<List<Pokemon>>

    @POST("pokemons")
    fun addPokemon(@Body pokemon: Pokemon): Call<Pokemon>

    @DELETE("pokemons/{numero_pokedex}")
    fun deletePokemon(@Path("numero_pokedex") pokemonId: Int): Call<Void>
}