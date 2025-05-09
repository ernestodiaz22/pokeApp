package com.example.apppokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.apppokedex.viewmodel.RegistroViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class RegistroViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RegistroViewModel

    @Before
    fun setup() {
        viewModel = RegistroViewModel()
    }

    // Función de extensión para LiveData
    private fun <T> LiveData<T>.getOrAwaitValue(): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : androidx.lifecycle.Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

    @Test
    fun `nombre vacio muestra error`() {
        viewModel.actualizarNombre("")
        viewModel.validarNombre()
        assertEquals("El nombre es obligatorio", viewModel.errorNombre.getOrAwaitValue())
    }

    @Test
    fun `nombre corto muestra error`() {
        viewModel.actualizarNombre("a")
        viewModel.validarNombre()
        assertEquals("Mínimo 2 caracteres", viewModel.errorNombre.getOrAwaitValue())
    }

    @Test
    fun `edad no numerica muestra error`() {
        viewModel.actualizarEdad("abc")
        viewModel.validarEdad()
        assertEquals("Edad debe ser un número", viewModel.errorEdad.getOrAwaitValue())
    }

    @Test
    fun `edad menor a 13 muestra error`() {
        viewModel.actualizarEdad("12")
        viewModel.validarEdad()
        assertEquals("Debes tener al menos 13 años", viewModel.errorEdad.getOrAwaitValue())
    }

    @Test
    fun `email invalido muestra error`() {
        viewModel.actualizarEmail("correo@invalido")
        viewModel.validarEmail()
        assertEquals("Email no válido", viewModel.errorEmail.getOrAwaitValue())
    }

    @Test
    fun `contrasenya debil muestra error`() {
        viewModel.actualizarContrasenya("1234567")
        viewModel.validarContrasenya()
        assertEquals("Mínimo 8 caracteres", viewModel.errorContrasenya.getOrAwaitValue())
    }

    @Test
    fun `contrasenya sin numero muestra error`() {
        viewModel.actualizarContrasenya("Contrasenya")
        viewModel.validarContrasenya()
        assertEquals("Debe contener al menos un número", viewModel.errorContrasenya.getOrAwaitValue())
    }

    @Test
    fun `registro exitoso con datos validos`() {
        // Datos válidos
        viewModel.actualizarNombre("Nombre")
        viewModel.actualizarApellidos("Apellidos")
        viewModel.actualizarEdad("25")
        viewModel.actualizarEmail("correo@valido.com")
        viewModel.actualizarContrasenya("Contrasenya123")
        viewModel.actualizarConfirmarContrasenya("Contrasenya123")

        // Ejecutar registro
        viewModel.registrarUsuario()

        // Verificar
        assertTrue(viewModel.registroExitoso.getOrAwaitValue() == true)
    }
}
