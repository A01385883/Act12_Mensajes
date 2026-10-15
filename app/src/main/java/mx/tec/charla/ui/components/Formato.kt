package mx.tec.charla.ui.components

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** «14:05». `en` viene en segundos (Python); Date quiere milisegundos. */
fun horaDe(en: Double): String =
    SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date((en * 1000).toLong()))

/** «quad-sic-well-accuracy.trycloudflare.com», sin el https:// que nadie necesita leer. */
fun sinEsquema(servidor: String): String = servidor.substringAfter("://")

/**
 * Convierte cualquier URL de imagen (relativa "/uploads/...", de localhost, o completa)
 * en una URL absoluta que Coil pueda descargar desde el servidor actual.
 */
fun resolverUrlImagen(url: String, servidor: String? = null): String {
    if (url.isBlank()) return url
    val base = servidor?.removeSuffix("/") ?: "http://10.0.2.2:8000"
    return when {
        url.startsWith("http://") || url.startsWith("https://") -> {
            if (base.isNotBlank() && (url.contains("localhost") || url.contains("127.0.0.1") || url.contains("10.0.2.2"))) {
                val path = url.substringAfter("://").substringAfter("/")
                "$base/$path"
            } else {
                url
            }
        }
        url.startsWith("/") -> "$base$url"
        else -> "$base/$url"
    }
}
