package com.example.apppokedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Favoritos extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void mostrarAyuda() {
        Intent intent = new Intent(this, Ayuda.class);
        startActivity(intent);
    }

    public void irBuscador() {
        Intent intent = new Intent(this, Buscador.class);
        startActivity(intent);
    }

    public void irInformacionPokemon() {
        Intent intent = new Intent(this, Informacion_entrenador.class);
        startActivity(intent);
    }

    public void irPokemonFavoritos() {
        Intent intent = new Intent(this, Favoritos.class);
        startActivity(intent);
    }

    public void salirAplicacion() {
        finishAffinity();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favoritos);

        // Configura el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configura el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPokemon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista de Pokemons
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(new Pokemon(1, R.drawable.bulbasaur, "Bulbasaur", "Planta", "Veneno"));
        pokemonList.add(new Pokemon(2, R.drawable.ivysaur, "Ivysaur", "Planta", "Veneno"));
        pokemonList.add(new Pokemon(3, R.drawable.venusaur, "Venusaur", "Planta", "Veneno"));
        pokemonList.add(new Pokemon(4, R.drawable.charmander, "Charmander", "Fuego", ""));
        pokemonList.add(new Pokemon(5, R.drawable.charmeleon, "Charmeleon", "Fuego", ""));
        pokemonList.add(new Pokemon(6, R.drawable.charizard, "Charizard", "Fuego", "Volador"));
        pokemonList.add(new Pokemon(7, R.drawable.squirtle, "Squirtle", "Agua", ""));
        pokemonList.add(new Pokemon(8, R.drawable.wartortle, "Wartortle", "Agua", ""));
        pokemonList.add(new Pokemon(9, R.drawable.blastoise, "Blastoise", "Agua", ""));
        pokemonList.add(new Pokemon(10, R.drawable.caterpie, "Caterpie", "Bicho", ""));
        pokemonList.add(new Pokemon(15, R.drawable.pikachu, "Pikachu", "Electrico", ""));

        // Vincula el adaptador con el RecyclerView
        PokemonAdapter adapter = new PokemonAdapter(pokemonList);
        recyclerView.setAdapter(adapter);

        // Manejo de padding con insets del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.buscador_pokemon) {
            irBuscador();
            return true;
        } else if (id == R.id.informacio_pokemon) {
            irInformacionPokemon();
            return true;
        } else if (id == R.id.pokemon_favoritos) {
            irPokemonFavoritos();
            return true;
        } else if (id == R.id.ayuda) {
            mostrarAyuda();
            return true;
        } else if (id == R.id.salir) {
            salirAplicacion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
