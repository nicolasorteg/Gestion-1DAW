package nicolasorteg.gestion1daw.common.viewmodels

import com.github.michaelbull.result.Result
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import nicolasorteg.gestion1daw.alumno.error.AlumnoError
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.service.AlumnoService
import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.routes.RoutesManager
import java.io.File

class PantallaInicialViewModel(
    private val alumnoService: AlumnoService
) {

    val alumnos: ObservableList<Alumno> = FXCollections.observableArrayList()

    fun cargarAlumnosDesdeArchivo(file: File): Result<List<Alumno>, AlumnoError> {
        val resultado = alumnoService.loadFromCsv(file)
        if (resultado.isOk) {
            alumnos.setAll(resultado.value)
        }
        return resultado
    }

    fun guardarAlumnosEnArchivo(file: File): Result<String, AlumnoError> {
        return alumnoService.saveToCsv(file)
    }

    fun actualizarAlumno(alumno: Alumno, nuevosDatos: Alumno): Result<Alumno, AlumnoError> {
        val resultado = alumnoService.update(alumno.id, nuevosDatos)
        if (resultado.isOk) {
            val index = alumnos.indexOfFirst { it.id == alumno.id }
            if (index >= 0) {
                alumnos[index] = resultado.value
            }
        }
        return resultado
    }

    fun crearAlumno(): Alumno {
        val nuevoAlumno = Alumno(
            id = generarNuevoId(),
            nombre = "",
            apellidos = "",
            fechaNacimiento = "",
            edad = 0,
            nacionalidad = "",
            fechaIncorporacion = "",
            modulos = emptyList(),
            expediente = Expediente(emptyMap(), 0.0, ""),
            notaMedia = 0.0,
            faltas = 0,
            retrasos = 0,
            partes = 0
        )

        val resultado = alumnoService.save(nuevoAlumno) // conectado con el back
        if (resultado.isOk) {
            alumnos.add(resultado.value)
            return resultado.value
        } else {
            return nuevoAlumno
        }
    }

    fun eliminarAlumno(alumno: Alumno): Boolean {
        val resultado = alumnoService.delete(alumno.id)
        if (resultado.isOk) {
            alumnos.removeIf { it.id == alumno.id }
            return true
        }
        return false
    }

    private fun generarNuevoId(): Int {
        val maxId = alumnos.maxOfOrNull { it.id } ?: 0
        return maxId + 1
    }

    fun abrirAcercaDe(stage: javafx.stage.Stage) {
        RoutesManager.initAcercaDeStage(stage)
    }
}
