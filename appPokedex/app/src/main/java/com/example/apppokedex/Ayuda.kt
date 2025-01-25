package com.example.apppokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Ayuda : AppCompatActivity() { // Aquí heredamos de AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayuda) // Aquí cargamos el layout
    }
}