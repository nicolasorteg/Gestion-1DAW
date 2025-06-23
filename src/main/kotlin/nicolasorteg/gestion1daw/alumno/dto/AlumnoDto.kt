package nicolasorteg.gestion1daw.alumno.dto

import nicolasorteg.gestion1daw.modulo.dto.ModuloDto
import nicolasorteg.gestion1daw.expediente.dto.ExpedienteDto

/**
 * DTO que representa un Alumno con todos sus datos relevantes.
 *
 * @property id Identificador único del alumno.
 * @property nombre Nombre del alumno.
 * @property apellidos Apellidos del alumno.
 * @property fechaNacimiento Fecha de nacimiento en formato String.
 * @property edad Edad actual del alumno.
 * @property nacionalidad Nacionalidad del alumno.
 * @property fechaIncorporacion Fecha en la que el alumno se incorporó.
 * @property modulos Lista de módulos que cursa el alumno.
 * @property expediente Expediente académico del alumno.
 * @property notaMedia Nota media actual del alumno.
 * @property faltas Número de faltas acumuladas.
 * @property retrasos Número de retrasos acumulados.
 * @property partes Número de partes disciplinarios.
 */
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
