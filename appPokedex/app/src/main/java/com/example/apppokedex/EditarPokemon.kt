package com.example.apppokedex

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarPokemonActivity : AppCompatActivity() {

    private lateinit var editTextImagen: EditText
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTipo1: EditText
    private lateinit var editTextTipo2: EditText
    private lateinit var buttonGuardar: Button
    private var pokemonSeleccionado: Pokemon? = null
    private var idPokemon: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pokemon)

        editTextImagen = findViewById(R.id.editTextImagen)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTipo1 = findViewById(R.id.editTextTipo1)
        editTextTipo2 = findViewById(R.id.editTextTipo2)
        buttonGuardar = findViewById(R.id.buttonGuardar)

        idPokemon = intent.getIntExtra("id_pokemon", 0)
        pokemonSeleccionado = intent.getSerializableExtra("pokemon") as? Pokemon

        if (pokemonSeleccionado != null) {
            llenarCamposConPokemon(pokemonSeleccionado!!)
        } else {
            // Si no tenemos el Pokémon, lo pedimos a la API
            cargarPokemonDesdeAPI(idPokemon)
        }

        buttonGuardar.setOnClickListener {
            actualizarPokemon()
        }
    }

    private fun cargarPokemonDesdeAPI(id: Int) {
        RetrofitClient.instance.obtenerPokemon(id)
            .enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        pokemonSeleccionado = response.body()
                        pokemonSeleccionado?.let { llenarCamposConPokemon(it) }
                    } else {
                        Toast.makeText(
                            this@EditarPokemonActivity,
                            "Error: Pokémon no encontrado",
                            Toast.LENGTH_LONG
                        ).show()
                        finish() // Cerrar la actividad si no se encuentra el Pokémon
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Toast.makeText(
                        this@EditarPokemonActivity,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
            })
    }

    private fun llenarCamposConPokemon(pokemon: Pokemon) {
        editTextImagen.setText(pokemon.imagen_pokemon)
        editTextNombre.setText(pokemon.nombrePokemon)
        editTextTipo1.setText(pokemon.tipo_1)
        editTextTipo2.setText(pokemon.tipo_2)
    }

    private fun actualizarPokemon() {
        val imagen = editTextImagen.text.toString()
        val nombre = editTextNombre.text.toString()
        val tipo1 = editTextTipo1.text.toString()
        val tipo2 = editTextTipo2.text.toString()

        val imagenFinal = if (imagen.isNotEmpty()) imagen else pokemonSeleccionado?.imagen_pokemon ?: ""
        val nombreFinal = if (nombre.isNotEmpty()) nombre else pokemonSeleccionado?.nombrePokemon ?: ""
        val tipo1Final = if (tipo1.isNotEmpty()) tipo1 else pokemonSeleccionado?.tipo_1 ?: ""
        val tipo2Final = if (tipo2.isNotEmpty()) tipo2 else pokemonSeleccionado?.tipo_2 ?: ""

        val pokemonActualizado = Pokemon(idPokemon, imagenFinal, nombreFinal, tipo1Final, tipo2Final)

        RetrofitClient.instance.actualizarPokemon(idPokemon, pokemonActualizado)
            .enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@EditarPokemonActivity, "Pokémon actualizado correctamente", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        val errorBody = response.errorBody()?.string() ?: "Sin detalles"
                        Toast.makeText(
                            this@EditarPokemonActivity,
                            "Error al actualizar: ${response.code()} - $errorBody",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Toast.makeText(
                        this@EditarPokemonActivity,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
