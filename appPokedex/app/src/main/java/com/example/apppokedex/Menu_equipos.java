package com.example.apppokedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu_equipos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_equipos);

        // Configura el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuración de insets para bordes de la pantalla
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void mostrarAyuda(View view) {
        Intent intent = new Intent(this, Ayuda.class);
        startActivity(intent);
    }

    public void irBuscador(View view) {
        Intent intent = new Intent(this, Buscador.class);
        startActivity(intent);
    }


    public void irInformacionPokemon(View view) {
        Intent intent = new Intent(this, Informacion_entrenador.class);
        startActivity(intent);
    }

    public void irPokemonFavoritos(View view) {
        Intent intent = new Intent(this, Favoritos.class);
        startActivity(intent);
    }

    public void salirAplicacion() {
        finishAffinity(); // Cierra todas las actividades
        System.exit(0); // Finaliza el proceso de la aplicación
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.buscador_pokemon) {
            irBuscador(null);
            return true;
        } else if (id == R.id.informacio_pokemon) {
            irInformacionPokemon(null);
            return true;
        } else if (id == R.id.pokemon_favoritos) {
            irPokemonFavoritos(null);
            return true;
        } else if (id == R.id.ayuda) {
            mostrarAyuda(null);
            return true;
        } else if (id == R.id.salir) {
            salirAplicacion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
