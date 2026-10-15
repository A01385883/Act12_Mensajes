package mx.tec.charla.ui.state

import kotlinx.serialization.json.Json
import mx.tec.charla.data.remote.Invitacion
import org.junit.Assert.assertEquals
import org.junit.Test

class SalaViewModelTest {

    private val json = Json { ignoreUnknownKeys = true; isLenient = true; coerceInputValues = true }

    @Test
    fun deserializacion_de_invitacion() {
        val invitacion = json.decodeFromString<Invitacion>("""{"url":"https://abc.trycloudflare.com"}""")
        assertEquals("https://abc.trycloudflare.com", invitacion.url)
    }

    @Test
    fun resolver_url_imagen_convierte_relativas_a_absolutas() {
        val urlRelativa = "/uploads/foto.jpg"
        val servidor = "http://10.0.2.2:8000"
        assertEquals("http://10.0.2.2:8000/uploads/foto.jpg", mx.tec.charla.ui.components.resolverUrlImagen(urlRelativa, servidor))
    }

    @Test
    fun resolver_url_imagen_reemplaza_localhost_con_servidor_real() {
        val urlLocalhost = "http://localhost:8000/uploads/foto.jpg"
        val servidor = "https://abc.trycloudflare.com"
        assertEquals("https://abc.trycloudflare.com/uploads/foto.jpg", mx.tec.charla.ui.components.resolverUrlImagen(urlLocalhost, servidor))
    }
}
