package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class informacion_pokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_informacion_pokemon)
        val botonEntrPok = findViewById<Button>(R.id.botonEntrPok)

        // Configurar el listener para el botón
        botonEntrPok.setOnClickListener {
            // Crear el Intent para navegar a la actividad de Inicio
            val intent = Intent(this, entrada_pokedex::class.java)
            startActivity(intent)
        }

    }
}