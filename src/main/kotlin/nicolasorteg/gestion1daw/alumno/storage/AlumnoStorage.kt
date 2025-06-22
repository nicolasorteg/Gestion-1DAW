package nicolasorteg.gestion1daw.alumno.storage

import com.github.michaelbull.result.Result
import nicolasorteg.gestion1daw.alumno.error.AlumnoError
import nicolasorteg.gestion1daw.alumno.models.Alumno
import java.io.File

interface AlumnoStorage {
    fun readFromFile(file: File): Result<List<Alumno>, AlumnoError>
    fun writeToFile (file: File, alumno: List<Alumno>): Result<String, AlumnoError>
}