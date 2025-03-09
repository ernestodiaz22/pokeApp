package com.example.apppokedex

import RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Buscador : AppCompatActivity() {

    private lateinit var recyclerViewPokemon: RecyclerView
    private lateinit var adapter: PokemonAdapter
    private var pokemonList: MutableList<Pokemon> = mutableListOf()

    private lateinit var editTextSearch: EditText
    private lateinit var imageButtonSearch: ImageButton
    private lateinit var spinnerTipo1: Spinner
    private lateinit var spinnerTipo2: Spinner
    private lateinit var spinnerRegion: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscador)


        // Configura el Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inicialización de vistas
        recyclerViewPokemon = findViewById(R.id.recyclerViewPokemon)
        editTextSearch = findViewById(R.id.editTextText2)
        imageButtonSearch = findViewById(R.id.imageButton5)
        spinnerTipo1 = findViewById(R.id.spinnerTipo1)
        spinnerTipo2 = findViewById(R.id.spinnerTipo2)
        spinnerRegion = findViewById(R.id.spinnerRegion)

        // RecyclerView setup
        recyclerViewPokemon.layoutManager = LinearLayoutManager(this)
        adapter = PokemonAdapter(pokemonList) { pokemon -> showDeleteConfirmationDialog(pokemon) }
        recyclerViewPokemon.adapter = adapter

        // Cargar los datos
        fetchPokemons()

        // Configurar el botón de búsqueda
        imageButtonSearch.setOnClickListener {
            searchPokemons()
        }
    }

    private fun fetchPokemons() {
        RetrofitClient.instance.getPokemonList().enqueue(object : Callback<List<Pokemon>> {
            override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
                if (response.isSuccessful) {
                    pokemonList.clear()
                    pokemonList.addAll(response.body() ?: emptyList())
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@Buscador, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                Toast.makeText(this@Buscador, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun searchPokemons() {
        val query = editTextSearch.text.toString().trim()

        // Aquí podrías filtrar la lista de Pokémon por los spinners si es necesario.
        // Ejemplo básico para filtrar por nombre (puedes expandir esto)
        if (query.isNotEmpty()) {
            val filteredList = pokemonList.filter { it.nombrePokemon.contains(query, ignoreCase = true) }
            adapter.updatePokemonList(filteredList)
        } else {
            adapter.updatePokemonList(pokemonList)
        }
    }

    private fun showDeleteConfirmationDialog(pokemon: Pokemon) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Pokémon")
        builder.setMessage("¿Estás seguro de que deseas eliminar a ${pokemon.nombrePokemon}?")
        builder.setPositiveButton("Sí") { _, _ -> deletePokemon(pokemon) }
        builder.setNegativeButton("No", null)
        builder.show()
    }

    private fun deletePokemon(pokemon: Pokemon) {
        // Simula la eliminación del Pokémon (ajusta según tu API)
        RetrofitClient.instance.deletePokemon(pokemon.num_pokedex).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Eliminar el Pokémon de la lista local
                    adapter.removePokemon(pokemon)
                    Toast.makeText(this@Buscador, "Pokémon eliminado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@Buscador, "Error al eliminar el Pokémon", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@Buscador, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun mostrarAyuda(view: View?) {
        val intent = Intent(this, Ayuda::class.java)
        startActivity(intent)
    }

    fun irBuscador(view: View?) {
        val intent = Intent(this, Buscador::class.java)
        startActivity(intent)
    }

    fun irInformacionPokemon(view: View?) {
        val intent = Intent(
            this,
            Informacion_entrenador::class.java
        )
        startActivity(intent)
    }

    fun irPokemonFavoritos(view: View?) {
        val intent = Intent(this, Favoritos::class.java)
        startActivity(intent)
    }

    fun salirAplicacion() {
        finishAffinity()
        System.exit(0) // Correcto en Java
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.buscador_pokemon) {
            irBuscador(null)
            return true
        } else if (id == R.id.informacio_pokemon) {
            irInformacionPokemon(null)
            return true
        } else if (id == R.id.pokemon_favoritos) {
            irPokemonFavoritos(null)
            return true
        } else if (id == R.id.ayuda) {
            mostrarAyuda(null)
            return true
        } else if (id == R.id.salir) {
            salirAplicacion()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun onAddPokemonClick(view: View) {
        val intent = Intent(this, AgregarPokemonActivity::class.java)
        startActivity(intent)
    }
}
