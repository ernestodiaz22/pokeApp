package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class buscador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_buscador)
        val botonInicio = findViewById<Button>(R.id.botonInicio)
        val botonSearch = findViewById<ImageButton>(R.id.searchButton)

        // Configurar el listener para el botón
        botonInicio.setOnClickListener {
            // Crear el Intent para navegar a la actividad de Inicio
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
        // Configurar el listener para el botón
        botonSearch.setOnClickListener {
            // Crear el Intent para navegar a la actividad de Inicio
            val intent = Intent(this, entrada_pokedex::class.java)
            startActivity(intent)
        }
    }
}