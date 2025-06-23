/*package nicolasorteg.gestion1daw.alumno.cache

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import nicolasorteg.gestion1daw.alumno.models.Alumno
import org.lighthousegames.logging.logging
import java.io.ObjectInputFilter

fun darAlumnosCache(
):Cache<Int, Alumno> {
    val size=
    val logger= logging()
    logger.debug { "Creando caché..." }
    return Caffeine.newBuilder().maximumSize(size).build()
}*/