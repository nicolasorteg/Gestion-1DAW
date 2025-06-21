package nicolasorteg.gestion1daw.alumno.dto

import nicolasorteg.gestion1daw.modulo.dto.ModuloDto
import nicolasorteg.gestion1daw.expediente.dto.ExpedienteDto

data class AlumnoDto(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val edad: Int,
    val nacionalidad: String,
    val fechaIncorporacion: String,
    val modulos: List<ModuloDto>,
    val expediente: ExpedienteDto,
    val notaMedia: Double,
    val faltas: Int,
    val retrasos: Int,
    val partes: Int
)
