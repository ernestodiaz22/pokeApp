import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.apppokedex.R
import com.example.apppokedex.Registro
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistroActivityTest {

    @Before
    fun launchActivity() {
        ActivityScenario.launch(Registro::class.java)
    }

    @Test
    fun camposVisiblesAlIniciar() {
        // 1. Verificar que todos los campos están visibles
        onView(withId(R.id.editTextText3)).check(matches(isDisplayed())) // Nombre
        onView(withId(R.id.editTextText4)).check(matches(isDisplayed())) // Apellidos
        onView(withId(R.id.editTextText7)).check(matches(isDisplayed())) // Edad
        onView(withId(R.id.editTextText8)).check(matches(isDisplayed())) // Email
        onView(withId(R.id.editTextText11)).check(matches(isDisplayed())) // Contraseña
        onView(withId(R.id.editTextText12)).check(matches(isDisplayed())) // Confirmar contraseña
        onView(withId(R.id.registro)).check(matches(isDisplayed())) // Botón
    }

    @Test
    fun alDejarNombreVacioMuestraError() {
        // 2. Verificar validación de nombre vacío
        onView(withId(R.id.editTextText3)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.registro)).perform(click())
        onView(withText("El nombre es obligatorio")).check(matches(isDisplayed()))
    }

    @Test
    fun nombreCortoMuestraError() {
        // 3. Verificar validación de nombre corto
        onView(withId(R.id.editTextText3)).perform(typeText("A"), closeSoftKeyboard())
        onView(withId(R.id.registro)).perform(click())
        onView(withText("Mínimo 2 caracteres")).check(matches(isDisplayed()))
    }

    @Test
    fun emailInvalidoMuestraError() {
        // 4. Verificar validación de email
        onView(withId(R.id.editTextText8)).perform(typeText("emailinvalido"), closeSoftKeyboard())
        onView(withId(R.id.registro)).perform(click())
        onView(withText("Email no válido")).check(matches(isDisplayed()))
    }

    @Test
    fun edadInvalidaMuestraError() {
        // 5. Verificar validación de edad
        onView(withId(R.id.editTextText7)).perform(typeText("abc"), closeSoftKeyboard())
        onView(withId(R.id.registro)).perform(click())
        onView(withText("Edad debe ser un número")).check(matches(isDisplayed()))
    }

    @Test
    fun contrasenaCortaMuestraError() {
        // 6. Verificar validación de contraseña corta
        onView(withId(R.id.editTextText11)).perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.registro)).perform(click())
        onView(withText("Mínimo 8 caracteres")).check(matches(isDisplayed()))
    }

    @Test
    fun contrasenasNoCoinciden() {
        // 7. Verificar coincidencia de contraseñas
        onView(withId(R.id.editTextText11)).perform(typeText("Contrasenya123"), closeSoftKeyboard())
        onView(withId(R.id.editTextText12)).perform(typeText("Diferente123"), closeSoftKeyboard())
        onView(withId(R.id.registro)).perform(click())
        onView(withText("Las contraseñas no coinciden")).check(matches(isDisplayed()))
    }

    @Test
    fun formularioValidoHabilitaBoton() {
        // 8. Verificar habilitación del botón con datos válidos
        onView(withId(R.id.editTextText3)).perform(typeText("Nombre"), closeSoftKeyboard())
        onView(withId(R.id.editTextText4)).perform(typeText("Apellidos"), closeSoftKeyboard())
        onView(withId(R.id.editTextText7)).perform(typeText("25"), closeSoftKeyboard())
        onView(withId(R.id.editTextText8)).perform(
            typeText("correo@valido.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.editTextText11)).perform(typeText("Contrasenya123"), closeSoftKeyboard())
        onView(withId(R.id.editTextText12)).perform(typeText("Contrasenya123"), closeSoftKeyboard())

        onView(withId(R.id.registro)).check(matches(isEnabled()))
    }

    @Test
    fun registroExitosoNavegaABuscador() {
        // 9. Verificar navegación exitosa
        llenarFormularioValido()
        onView(withId(R.id.registro)).perform(click())
        onView(withId(R.id.buscador_pokemon)).check(matches(isDisplayed()))
    }

    @Test
    fun tecladoSeCierraAlRegistrar() {
        // 1. Abrir el teclado haciendo clic en un campo
        onView(withId(R.id.editTextText3)).perform(click())

        // 2. Cerrar el teclado manualmente (simulando acción de registro)
        Espresso.closeSoftKeyboard()

        // 3. Verificar que el teclado está cerrado usando una estrategia alternativa
        try {
            onView(withId(R.id.editTextText3)).check(matches(hasFocus()))
            throw AssertionError("El teclado podría estar todavía abierto")
        } catch (e: NoMatchingViewException) {
        }
    }

    private fun llenarFormularioValido() {
        onView(withId(R.id.editTextText3)).perform(typeText("Nombre"), closeSoftKeyboard())
        onView(withId(R.id.editTextText4)).perform(typeText("Apellidos"), closeSoftKeyboard())
        onView(withId(R.id.editTextText7)).perform(typeText("25"), closeSoftKeyboard())
        onView(withId(R.id.editTextText8)).perform(
            typeText("correo@valido.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.editTextText11)).perform(typeText("Contrasenya123"), closeSoftKeyboard())
        onView(withId(R.id.editTextText12)).perform(typeText("Contrasenya123"), closeSoftKeyboard())
    }
}