package mx.tec.charla.ui.state

import kotlinx.serialization.json.Json
import mx.tec.charla.data.remote.EstadoSolicitud
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class UnirseViewModelTest {

    private val json = Json { ignoreUnknownKeys = true; isLenient = true; coerceInputValues = true }

    @Test
    fun deserializacion_de_estado_solicitud_pendiente() {
        val pendiente = json.decodeFromString<EstadoSolicitud>("""{"id":"IS_cy","nickname":"eli","estado":"pendiente"}""")
        assertEquals("IS_cy", pendiente.id)
        assertEquals("pendiente", pendiente.estado)
        assertNull(pendiente.token)
    }

    @Test
    fun deserializacion_de_estado_solicitud_aprobada() {
        val aprobada = json.decodeFromString<EstadoSolicitud>("""{"id":"IS_cy","nickname":"eli","estado":"aprobada","token":"token-123"}""")
        assertEquals("IS_cy", aprobada.id)
        assertEquals("aprobada", aprobada.estado)
        assertEquals("token-123", aprobada.token)
    }
}
