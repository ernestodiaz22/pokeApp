package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.apppokedex.Login

class Carga : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Establecer el layout de la pantalla de carga
        setContentView(R.layout.activity_carga)

        // Retardo de 2 segundos antes de ir a la actividad principal
        Handler().postDelayed({
            // Iniciar la actividad principal
            val intent = Intent(this@Carga, Login::class.java)
            startActivity(intent)
            finish()  // Finalizar la SplashActivity para que no se pueda volver atrás
        }, 2000)  // 2000 milisegundos (2 segundos)
    }
}
