package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        // Encontrar el botón en la interfaz
        val button = findViewById<Button>(R.id.button)
        val buttonRegistro = findViewById<Button>(R.id.registrarse)

        // Configurar el listener para el botón
        button.setOnClickListener {
            // Crear el Intent para navegar a la actividad de Inicio
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
        // Configurar el listener para el botón
        buttonRegistro.setOnClickListener {
            // Crear el Intent para navegar a la actividad de registro
            val intent = Intent(this, registro::class.java)
            startActivity(intent)
        }
    }
}