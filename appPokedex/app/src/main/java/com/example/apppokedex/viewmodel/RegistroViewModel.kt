package com.example.apppokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistroViewModel : ViewModel() {
    private var nombre: String = ""
    private var apellidos: String = ""
    private var edad: String = ""
    private var email: String = ""
    private var pais: String = ""
    private var ciudad: String = ""
    private var contrasenya: String = ""
    private var confirmarContrasenya: String = ""


    private val _camposValidos = MutableLiveData<Boolean>()
    val camposValidos: LiveData<Boolean> = _camposValidos

    init {
        _camposValidos.value = false
    }


    fun actualizarEstadoBoton() {
        _camposValidos.value = _errorNombre.value == null &&
                _errorApellidos.value == null &&
                _errorEdad.value == null &&
                _errorEmail.value == null &&
                _errorContrasenya.value == null &&
                _errorConfirmarContrasenya.value == null &&
                nombre.isNotBlank() &&
                apellidos.isNotBlank() &&
                edad.isNotBlank() &&
                email.isNotBlank() &&
                contrasenya.isNotBlank() &&
                confirmarContrasenya.isNotBlank()
    }

    //  errores
    private val _errorNombre = MutableLiveData<String?>()
    val errorNombre: LiveData<String?> = _errorNombre

    private val _errorApellidos = MutableLiveData<String?>()
    val errorApellidos: LiveData<String?> = _errorApellidos

    private val _errorEdad = MutableLiveData<String?>()
    val errorEdad: LiveData<String?> = _errorEdad

    private val _errorEmail = MutableLiveData<String?>()
    val errorEmail: LiveData<String?> = _errorEmail

    private val _errorPais = MutableLiveData<String?>()
    val errorPais: LiveData<String?> = _errorPais

    private val _errorCiudad = MutableLiveData<String?>()
    val errorCiudad: LiveData<String?> = _errorCiudad

    private val _errorContrasenya = MutableLiveData<String?>()
    val errorContrasenya: LiveData<String?> = _errorContrasenya

    private val _errorConfirmarContrasenya = MutableLiveData<String?>()
    val errorConfirmarContrasenya: LiveData<String?> = _errorConfirmarContrasenya


    private val _registroExitoso = MutableLiveData<Boolean>()
    val registroExitoso: LiveData<Boolean> = _registroExitoso

    // Actualizar valores
    fun actualizarNombre(nom: String) {
        nombre = nom
        actualizarEstadoBoton()
    }

    fun actualizarApellidos(ape: String) {
        apellidos = ape
        actualizarEstadoBoton()
    }

    fun actualizarEdad(ed: String) {
        edad = ed
        actualizarEstadoBoton()
    }

    fun actualizarEmail(em: String) {
        email = em
        actualizarEstadoBoton()
    }

    fun actualizarPais(pa: String) {
        pais = pa
        actualizarEstadoBoton()
    }

    fun actualizarCiudad(ci: String) {
        ciudad = ci
        actualizarEstadoBoton()
    }

    fun actualizarContrasenya(con: String) {
        contrasenya = con
        actualizarEstadoBoton()
    }

    fun actualizarConfirmarContrasenya(con: String) {
        confirmarContrasenya = con
        actualizarEstadoBoton()
    }

    // Validaciones
    fun validarNombre() {
        _errorNombre.value = when {
            nombre.isBlank() -> "El nombre es obligatorio"
            nombre.length < 2 -> "Mínimo 2 caracteres"
            else -> null
        }
        actualizarEstadoBoton()
    }

    fun validarApellidos() {
        _errorApellidos.value = when {
            apellidos.isBlank() -> "Los apellidos son obligatorios"
            else -> null
        }
        actualizarEstadoBoton()
    }

    fun validarEdad() {
        _errorEdad.value = when {
            edad.isBlank() -> "La edad es obligatoria"
            edad.toIntOrNull() == null -> "Edad debe ser un número"
            edad.toInt() < 13 -> "Debes tener al menos 13 años"
            edad.toInt() > 120 -> "Edad no válida"
            else -> null
        }
        actualizarEstadoBoton()
    }

    fun validarEmail() {
        val emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        _errorEmail.value = when {
            email.isBlank() -> "El email es obligatorio"
            !email.matches(emailPattern.toRegex()) -> "Email no válido"
            else -> null
        }
        actualizarEstadoBoton()
    }

    fun validarContrasenya() {
        _errorContrasenya.value = when {
            contrasenya.isBlank() -> "La contraseña es obligatoria"
            contrasenya.length < 8 -> "Mínimo 8 caracteres"
            !contrasenya.any { it.isDigit() } -> "Debe contener al menos un número"
            !contrasenya.any { it.isLetter() } -> "Debe contener al menos una letra"
            else -> null
        }
        actualizarEstadoBoton()
    }

    fun validarConfirmarContrasenya() {
        _errorConfirmarContrasenya.value = when {
            confirmarContrasenya != contrasenya -> "Las contraseñas no coinciden"
            else -> null
        }
        actualizarEstadoBoton()
    }

    fun registrarUsuario() {
        validarNombre()
        validarApellidos()
        validarEdad()
        validarEmail()
        validarContrasenya()
        validarConfirmarContrasenya()

        if (_errorNombre.value == null &&
            _errorApellidos.value == null &&
            _errorEdad.value == null &&
            _errorEmail.value == null &&
            _errorContrasenya.value == null &&
            _errorConfirmarContrasenya.value == null) {
            _registroExitoso.value = true
        }
    }
}
