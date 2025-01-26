package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlin.system.exitProcess

class informacion_entrenador : AppCompatActivity() {
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
        finishAffinity()  // Cierra la actividad actual
        exitProcess(0)  // Termina el proceso de la aplicación
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
        setContentView(R.layout.activity_informacion_entrenador)


        // Configura el Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val equipoPok = findViewById<Button>(R.id.equipoPok)

        // Configura el OnClickListener
        equipoPok.setOnClickListener {
            // Inicia la actividad equipos_pokemon
            val intent = Intent(this, equipo_pokemon::class.java)
            startActivity(intent)
        }


    }
}