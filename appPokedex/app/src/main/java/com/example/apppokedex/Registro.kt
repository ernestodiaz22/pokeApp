package com.example.apppokedex

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.apppokedex.databinding.ActivityRegistroBinding
import com.example.apppokedex.viewmodel.RegistroViewModel

class Registro : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private val viewModel: RegistroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
        setupUI()
    }

    private fun setupUI() {
        binding.editTextText12.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.registrarUsuario()
                true
            } else {
                false
            }
        }
    }

    private fun setupObservers() {
        viewModel.errorNombre.observe(this, Observer { error ->
            binding.textView12.error = error
            binding.textView12.isVisible = error != null
            viewModel.actualizarEstadoBoton()
        })

        viewModel.errorApellidos.observe(this, Observer { error ->
            binding.textView13.error = error
            binding.textView13.isVisible = error != null
            viewModel.actualizarEstadoBoton()
        })

        viewModel.errorEdad.observe(this, Observer { error ->
            binding.textView18.error = error
            binding.textView18.isVisible = error != null
            viewModel.actualizarEstadoBoton()
        })

        viewModel.errorEmail.observe(this, Observer { error ->
            binding.textView19.error = error
            binding.textView19.isVisible = error != null
            viewModel.actualizarEstadoBoton()
        })

        viewModel.errorContrasenya.observe(this, Observer { error ->
            binding.textView22.error = error
            binding.textView22.isVisible = error != null
            viewModel.actualizarEstadoBoton()
        })

        viewModel.errorConfirmarContrasenya.observe(this, Observer { error ->
            binding.textView23.error = error
            binding.textView23.isVisible = error != null
            viewModel.actualizarEstadoBoton()
        })

        viewModel.registroExitoso.observe(this, Observer { exito ->
            if (exito) {
                navigateToBuscador()
            }
        })

        viewModel.camposValidos.observe(this, Observer { validos ->
            binding.registro.isEnabled = validos ?: false
        })
    }

    private fun setupListeners() {
        binding.editTextText3.apply {
            onTextChanged { text ->
                viewModel.actualizarNombre(text)
            }
            setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) viewModel.validarNombre() }
        }

        binding.editTextText4.apply {
            onTextChanged { text -> viewModel.actualizarApellidos(text) }
            setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) viewModel.validarApellidos() }
        }

        binding.editTextText7.apply {
            onTextChanged { text -> viewModel.actualizarEdad(text) }
            setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) viewModel.validarEdad() }
        }

        binding.editTextText8.apply {
            onTextChanged { text -> viewModel.actualizarEmail(text) }
            setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) viewModel.validarEmail() }
        }

        binding.editTextText11.apply {
            onTextChanged { text -> viewModel.actualizarContrasenya(text) }
            setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) viewModel.validarContrasenya() }
        }

        binding.editTextText12.apply {
            onTextChanged { text -> viewModel.actualizarConfirmarContrasenya(text) }
            setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) viewModel.validarConfirmarContrasenya() }
        }

        binding.registro.setOnClickListener {
            hideKeyboard()
            viewModel.registrarUsuario()
        }
    }

    private fun navigateToBuscador() {
        startActivity(Intent(this, Buscador::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }

    private fun hideKeyboard() {
        currentFocus?.let {
            val imm = getSystemService(InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}


fun android.widget.EditText.onTextChanged(listener: (String) -> Unit) {
    this.addTextChangedListener(object : android.text.TextWatcher {
        override fun afterTextChanged(s: android.text.Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            listener(s?.toString() ?: "")
        }
    })
}