package nicolasorteg.gestion1daw.viewmodels

import com.github.michaelbull.result.Result
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.stage.Stage
import nicolasorteg.gestion1daw.alumno.error.AlumnoError
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.storage.AlumnoStorageCsv
import nicolasorteg.gestion1daw.routes.RoutesManager
import java.io.File

class PantallaInicialViewModel {

    val alumnos: ObservableList<Alumno> = FXCollections.observableArrayList()

    fun cargarAlumnosDesdeArchivo(file: File): Result<List<Alumno>, AlumnoError> {
        val storage = AlumnoStorageCsv()
        val resultado = storage.readFromFile(file)
        if (resultado.isOk) {
            alumnos.setAll(resultado.value)
        }
        return resultado
    }

    fun guardarAlumnosEnArchivo(file: File): Result<String, AlumnoError> {
        val storage = AlumnoStorageCsv()
        return storage.writeToFile(file, alumnos.toList())
    }

    fun actualizarAlumno(alumno: Alumno, nuevosDatos: Alumno) {
        alumno.nombre = nuevosDatos.nombre
        alumno.apellidos = nuevosDatos.apellidos
        alumno.edad = nuevosDatos.edad
        alumno.fechaNacimiento = nuevosDatos.fechaNacimiento
        alumno.nacionalidad = nuevosDatos.nacionalidad
        alumno.fechaIncorporacion = nuevosDatos.fechaIncorporacion
        alumno.notaMedia = nuevosDatos.notaMedia
        alumno.faltas = nuevosDatos.faltas
        alumno.retrasos = nuevosDatos.retrasos
        alumno.partes = nuevosDatos.partes
    }

    fun abrirAcercaDe(stage: Stage) {
        RoutesManager.initAcercaDeStage(stage)
    }
}
