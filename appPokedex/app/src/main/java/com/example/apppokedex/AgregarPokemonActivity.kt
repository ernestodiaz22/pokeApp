package com.example.apppokedex



import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgregarPokemonActivity : AppCompatActivity() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextTipo1: EditText
    private lateinit var editTextTipo2: EditText
    private lateinit var editTextImagen: EditText
    private lateinit var editTextNumPokedex: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_pokemon)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTipo1 = findViewById(R.id.editTextTipo1)
        editTextTipo2 = findViewById(R.id.editTextTipo2)
        editTextImagen = findViewById(R.id.editTextImagen)
        editTextNumPokedex = findViewById(R.id.editTextNumPokedex)

        val dbHelper = DBHelper(this)
        dbHelper.registrarVisita("AgregarPokemon")
    }

    fun onSavePokemonClick(view: View) {
        val numPokedex = editTextNumPokedex.text.toString().toIntOrNull() ?: 0

        val nombre = editTextNombre.text.toString()
        val tipo1 = editTextTipo1.text.toString()
        val tipo2 = editTextTipo2.text.toString()
        val imagen = editTextImagen.text.toString()

        if (nombre.isEmpty() || tipo1.isEmpty() || imagen.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoPokemon = Pokemon(

            num_pokedex = numPokedex,
            nombrePokemon = nombre,
            tipo_1 = tipo1,
            tipo_2 = if (tipo2.isEmpty()) null else tipo2,
            imagen_pokemon = imagen
        )

        RetrofitClient.instance.addPokemon(nuevoPokemon).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AgregarPokemonActivity, "Pokémon añadido correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {

                    val errorMessage = response.errorBody()?.string() ?: "Desconocido"
                    val statusCode = response.code()

                    Toast.makeText(this@AgregarPokemonActivity, "Error al añadir el Pokémon: Código $statusCode - $errorMessage", Toast.LENGTH_SHORT).show()


                    Log.e("AgregarPokemon", "Error: Código $statusCode, Mensaje: $errorMessage")
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Toast.makeText(this@AgregarPokemonActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
