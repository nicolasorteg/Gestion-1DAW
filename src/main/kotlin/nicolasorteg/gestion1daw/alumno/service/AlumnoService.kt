package nicolasorteg.gestion1daw.alumno.service

import com.github.michaelbull.result.Result
import nicolasorteg.gestion1daw.alumno.error.AlumnoError
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.common.service.Service
import java.io.File

/**
 * Interfaz que extiende Service y añade métodos para
 * importar y exportar alumnos desde/hacia CSV.
 */
interface AlumnoService : Service<Alumno, AlumnoError, Int> {
    fun loadFromCsv(file: File): Result<List<Alumno>, AlumnoError>
    fun saveToCsv(file: File): Result<String, AlumnoError>
}