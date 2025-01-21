package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        val trainerButton = findViewById<Button>(R.id.infTrainer)
        val searchPokButton = findViewById<Button>(R.id.searchPok)
        val favPok = findViewById<Button>(R.id.favPok)
        val exit = findViewById<Button>(R.id.exit)

        // Configurar el listener para el botón
        trainerButton.setOnClickListener {
            // Crear el Intent para navegar a la actividad de Inicio
            val intent = Intent(this, informacion_entrenador::class.java)
            startActivity(intent)
        }
        // Configurar el listener para el botón
        searchPokButton.setOnClickListener {
            // Crear el Intent para navegar a la actividad de registro
            val intent = Intent(this, buscador::class.java)
            startActivity(intent)
        }
        // Configurar el listener para el botón
        favPok.setOnClickListener {
            // Crear el Intent para navegar a la actividad de Inicio
            val intent = Intent(this, this::class.java)
            startActivity(intent)
        }
        // Configurar el listener para el botón
        exit.setOnClickListener {
            finishAffinity()
            System.exit(0)
        }
    }
}