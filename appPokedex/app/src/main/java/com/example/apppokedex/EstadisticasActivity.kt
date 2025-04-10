package com.example.apppokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EstadisticasActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private val _estadisticas = MutableLiveData<Map<String, Int>>()
    val estadisticas: LiveData<Map<String, Int>> get() = _estadisticas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        dbHelper = DBHelper(applicationContext)
    }

    fun cargarEstadisticas() {
        val stats = dbHelper.obtenerEstadisticas()
        _estadisticas.value = stats
    }
}
