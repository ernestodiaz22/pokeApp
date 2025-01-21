package com.example.apppokedex

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class buscador : AppCompatActivity() {
    val spinnerTipo1 : Spinner = findViewById(R.id.spinnerTipo1)
    val spinnerTipo2 : Spinner = findViewById(R.id.spinnerTipo2)
    val tiposPokemon = listOf("Selecciona el tipo","Acero","Agua","Bicho","Dragón","Eléctrico","Fantasma","Fuego","","Hada","","Hielo","Lucha","Normal","Planta","Psíquico","Roca","Siniestro","Tierra","Veneno", "Volador")
    val spinnerRegion : Spinner = findViewById(R.id.spinnerRegion)
    val regiones = listOf("Kanto","Islas Sete","Johto","Hoenn","Sinnoh","Teselia","Kalos","Alola","Galar","Hisui")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_buscador)

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

        // diseño del desplegable
        adapterTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo1.adapter = adapterTipos
        spinnerTipo2.adapter = adapterTipos
        adapterRegiones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRegion.adapter = adapterRegiones

        spinnerTipo1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position == 0) {
                // Texto por defecto seleccionado
                } else {
                    val opcionSeleccionada = tiposPokemon[position]
                // Manejar la opción seleccionada
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                    // Nada seleccionado
            }
        }

        spinnerTipo2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long){
                if(position == 0){
                    //texto por defecto
                }else{
                    val opcionSeleccionada = tiposPokemon[position]
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>){

            }
        }

        spinnerRegion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long){
                if(position == 0){
                    //texto por defecto
                }else{
                    val opcionSeleccionada = regiones[position]
                }
            }
            override  fun onNothingSelected(parent: AdapterView<*>){

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
    }
