package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class informacion_entrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_informacion_entrenador)
        val equipoPok = findViewById<Button>(R.id.equipoPok)

        // Configurar el listener para el botón
        equipoPok.setOnClickListener {
            // Crear el Intent para navegar a la actividad de Inicio
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }
}