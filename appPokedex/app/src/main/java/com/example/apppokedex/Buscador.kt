package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Buscador : AppCompatActivity() {
    private lateinit var recyclerViewPokemon: RecyclerView
    private lateinit var adapter: PokemonAdapter
    private var pokemonList: MutableList<Pokemon> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_buscador)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerViewPokemon = findViewById(R.id.recyclerViewPokemon)
        recyclerViewPokemon.layoutManager = LinearLayoutManager(this)

        // Asegúrate de que el adapter se asigna antes de que los datos se carguen
        adapter = PokemonAdapter(pokemonList)
        recyclerViewPokemon.adapter = adapter // Asigna el adapter al RecyclerView

        fetchPokemons() // Llamar a la API para cargar los datos
        Log.d("PokemonAdapter", "Datos de Pokémon: ${pokemonList}")


    }


    private fun fetchPokemons() {
        RetrofitClient.instance.getPokemonList().enqueue(object : Callback<List<Pokemon>> {
            override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
                if (response.isSuccessful) {
                    pokemonList.clear()
                    pokemonList.addAll(response.body() ?: emptyList())
                    adapter.notifyDataSetChanged()
                    // Esto debería estar donde haces la solicitud de la API, antes de procesar los datos
                    Log.d("PokemonApi", "Respuesta cruda de la API: ${response.body()}")

                } else {
                    // Esto debería estar donde haces la solicitud de la API, antes de procesar los datos
                    Log.d("PokemonApi", "Respuesta cruda de la API: ${response.body()}")

                    Toast.makeText(this@Buscador, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                Toast.makeText(this@Buscador, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("API_ERROR", t.message.toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.buscador_pokemon -> { irBuscador(); true }
            R.id.informacio_pokemon -> { irInformacionPokemon(); true }
            R.id.pokemon_favoritos -> { irPokemonFavoritos(); true }
            R.id.ayuda -> { mostrarAyuda(); true }
            R.id.salir -> { salirAplicacion(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarAyuda() {
        startActivity(Intent(this, Ayuda::class.java))
    }
    private fun irBuscador() {
        startActivity(Intent(this, Buscador::class.java))
    }
    private fun irInformacionPokemon() {
        startActivity(Intent(this, Informacion_entrenador::class.java))
    }
    private fun irPokemonFavoritos() {
        startActivity(Intent(this, Favoritos::class.java))
    }
    private fun salirAplicacion() {
        finishAffinity()
    }
}
