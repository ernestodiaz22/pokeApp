package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class entrada_pokedex : AppCompatActivity() {
    //añadir menú
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    fun mostrarAyuda(view: View?) {
        val intent = Intent(this, Ayuda::class.java)
        startActivity(intent)
    }
    fun irBuscador(view: View?) {
        val intent = Intent(this, buscador::class.java)
        startActivity(intent)
    }
    fun irInformacionPokemon(view: View?) {
        val intent = Intent(this, informacion_entrenador::class.java)
        startActivity(intent)
    }
    fun irPokemonFavoritos(view: View?) {
        val intent = Intent(this, favoritos::class.java)
        startActivity(intent)
    }
    fun salirAplicacion() {
        finishAffinity()
        exitProcess(0)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.buscador_pokemon) {
            irBuscador(null)
            return true
        }
        if (id == R.id.informacio_pokemon) {
            irInformacionPokemon(null)
            return true
        }
        if (id == R.id.pokemon_favoritos) {
            irPokemonFavoritos(null)
            return true
        }
        if (id == R.id.ayuda) {
            mostrarAyuda(null)
            return true
        }
        if (id == R.id.salir) {
            salirAplicacion()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_entrada_pokedex)

        // Configura el Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}