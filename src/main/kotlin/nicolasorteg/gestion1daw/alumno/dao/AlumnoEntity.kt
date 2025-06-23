package nicolasorteg.gestion1daw.alumno.dao

import nicolasorteg.gestion1daw.expediente.dao.ExpedienteEntity
import nicolasorteg.gestion1daw.modulo.dao.ModuloEntity

/**
 * Es la entidad de un alumno tal como se almacena y recupera de la BD.
 *
 * @property id Identificador único del alumno.
 * @property nombre Nombre del alumno.
 * @property apellidos Apellidos del alumno.
 * @property fechaNacimiento Fecha de nacimiento en formato `yyyy-MM-dd`.
 * @property edad Edad actual del alumno.
 * @property nacionalidad Nacionalidad del alumno.
 * @property fechaIncorporacion Fecha en la que el alumno se incorporó al centro.
 * @property modulos Lista de módulos en los que el alumno está inscrito.
 * @property expediente Información detallada del expediente académico del alumno.
 * @property notaMedia Nota media del alumno en todos los módulos.
 * @property faltas Número de faltas acumuladas.
 * @property retrasos Número de veces que el alumno llegó tarde.
 * @property partes Número de partes disciplinarios registrados.
 */
data class AlumnoEntity(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val edad: Int,
    val nacionalidad: String,
    val fechaIncorporacion: String,
    val modulos: List<ModuloEntity>,
    val expediente: ExpedienteEntity,
    val notaMedia: Double,
    val faltas: Int,
    val retrasos: Int,
    val partes: Int
)