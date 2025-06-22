package nicolasorteg.gestion1daw.alumno.service

import com.github.michaelbull.result.*
import nicolasorteg.gestion1daw.alumno.error.AlumnoError
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.repository.AlumnoRepositoryImpl
import nicolasorteg.gestion1daw.alumno.storage.AlumnoStorageCsv
import nicolasorteg.gestion1daw.alumno.validator.AlumnoValidator
import org.lighthousegames.logging.logging
import java.io.File

class AlumnoServiceImpl(
    private val repositorio: AlumnoRepositoryImpl,
    private val validator: AlumnoValidator,
    private val storage: AlumnoStorageCsv
): AlumnoService<Alumno, AlumnoError, Int> {

    private val logger = logging()

    override fun getAll(): Result<List<Alumno>, AlumnoError> {
        logger.debug { "🔍 Obteniendo todos los alumnos desde el repositorio" }
        return Ok(repositorio.getAll())
    }

    override fun getByID(id: Int): Result<Alumno, AlumnoError> {
        logger.debug { "🔍 Buscando alumno con id = $id" }
        val alumno = repositorio.getById(id)
        return if (alumno != null) {
            Ok(alumno)
        } else {
            Err(AlumnoError.AlumnoServiceException("No se encontró el alumno con id $id"))
        }
    }

    override fun save(item: Alumno): Result<Alumno, AlumnoError> {
        logger.debug { "💾 Guardando alumno: $item" }

        return validator.validate(item)
            .andThen {
                try {
                    Ok(repositorio.save(it))
                } catch (e: Exception) {
                    logger.error { "Error al guardar el alumno: ${e.message}" }
                    Err(AlumnoError.AlumnoServiceException("Error al guardar el alumno: ${e.message}"))
                }
            }
    }

    override fun update(id: Int, item: Alumno): Result<Alumno, AlumnoError> {
        logger.debug { "✏️ Actualizando alumno con id = $id" }

        return validator.validate(item)
            .andThen {
                val updated = repositorio.update(it, id)
                if (updated != null) {
                    Ok(updated)
                } else {
                    Err(AlumnoError.AlumnoServiceException("No se pudo actualizar. Alumno con id $id no encontrado"))
                }
            }
    }

    override fun delete(id: Int): Result<Alumno, AlumnoError> {
        logger.debug { "🗑️ Eliminando alumno con id = $id" }

        return try {
            val deleted = repositorio.delete(id)
            if (deleted != null) {
                Ok(deleted)
            } else {
                Err(AlumnoError.AlumnoServiceException("No se pudo eliminar. Alumno con id $id no encontrado"))
            }
        } catch (e: Exception) {
            logger.error { "Error al eliminar alumno: ${e.message}" }
            Err(AlumnoError.AlumnoServiceException("Error al eliminar alumno: ${e.message}"))
        }
    }

    fun loadFromCsv(file: File): Result<List<Alumno>, AlumnoError> {
        logger.debug { "📂 Cargando alumnos desde archivo CSV: ${file.name}" }
        return storage.readFromFile(file)
    }

    fun saveToCsv(file: File): Result<String, AlumnoError> {
        logger.debug { "📁 Guardando alumnos a archivo CSV: ${file.name}" }
        return storage.writeToFile(file, repositorio.getAll())
    }
}
