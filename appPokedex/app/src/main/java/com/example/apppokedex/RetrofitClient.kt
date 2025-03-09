import com.example.apppokedex.PokemonApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import java.security.cert.X509Certificate

object RetrofitClient {

    private const val BASE_URL = "https://50.19.251.11/" // Cambia esta URL si es necesario

    // Configurar un cliente OkHttp que desactive la validación SSL
    private fun getUnsafeOkHttpClient(): OkHttpClient {
        // Crea un TrustManager que no valide los certificados SSL
        val trustAllCertificates = object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        }

        // Instala el TrustManager y configura el contexto SSL
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf(trustAllCertificates), java.security.SecureRandom())

        // Configura un OkHttpClient con el SSLContext personalizado
        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCertificates)
            .hostnameVerifier { _, _ -> true } // Desactiva la verificación del hostname
            .build()
    }

    // Instancia de Retrofit con cliente OkHttp sin validación SSL
    val instance: PokemonApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getUnsafeOkHttpClient())  // Usa el cliente con SSL desactivado
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PokemonApiService::class.java)
    }
}
