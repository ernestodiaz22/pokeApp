package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Navegar directamente a la pantalla de Buscador
        val intent = Intent(this, buscador::class.java)
        startActivity(intent)

        // Finalizar MainActivity para evitar regresar a ella
        finish()
    }
}
