package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess


class buscador : AppCompatActivity() {
    private lateinit var recyclerViewPokemon: RecyclerView
    private lateinit var adapter: PokemonAdapter



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
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_buscador)

        // Configura el Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        // Configura el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewPokemon)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val pokemonList = listOf(
            Pokemon(1, R.drawable.bulbasaur, "Bulbasaur", "Planta", "Veneno"),
            Pokemon(4, R.drawable.charmander, "Charmander", "Fuego", ""),
            Pokemon(7, R.drawable.squirtle, "Squirtle", "Agua", ""),
            Pokemon(25, R.drawable.pikachu, "Pikachu", "Eléctrico", ""),
            Pokemon(6, R.drawable.charizard, "Charizard", "Fuego", "Volador")
        )

        // Vincula el adaptador con el RecyclerView
        recyclerView.adapter = PokemonAdapter(pokemonList)

        // Inicializa las vistas aquí, después de setContentView
        val spinnerTipo1: Spinner = findViewById(R.id.spinnerTipo1)
        val spinnerTipo2: Spinner = findViewById(R.id.spinnerTipo2)
        val spinnerRegion: Spinner = findViewById(R.id.spinnerRegion)

        // Listas para los Spinners
        val tiposPokemon = listOf(
            "Selecciona el tipo", "Acero", "Agua", "Bicho", "Dragón", "Eléctrico", "Fantasma",
            "Fuego", "Hada", "Hielo", "Lucha", "Normal", "Planta", "Psíquico",
            "Roca", "Siniestro", "Tierra", "Veneno", "Volador"
        )
        val regiones = listOf(
            "Kanto", "Islas Sete", "Johto", "Hoenn", "Sinnoh", "Teselia", "Kalos",
            "Alola", "Galar", "Hisui"
        )

        //conectar las opciones del spinner
        val adapterTipos = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, //diseño del select
            tiposPokemon
        )
        val adapterRegiones = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            regiones
        )


        // Diseño del desplegable
        adapterTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterRegiones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignar los adaptadores a los Spinners
        spinnerTipo1.adapter = adapterTipos
        spinnerTipo2.adapter = adapterTipos
        spinnerRegion.adapter = adapterRegiones

        // Configurar listeners para los Spinners
        spinnerTipo1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val opcionSeleccionada = tiposPokemon[position]
                    // Manejar la opción seleccionada
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nada seleccionado
            }
        }

        spinnerTipo2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val opcionSeleccionada = tiposPokemon[position]
                    // Manejar la opción seleccionada
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nada seleccionado
            }
        }

        spinnerRegion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val opcionSeleccionada = regiones[position]
                    // Manejar la opción seleccionada
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nada seleccionado
            }
        }
    }
}
       /* val botonInicio = findViewById<Button>(R.id.botonInicio)
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
        }*/



