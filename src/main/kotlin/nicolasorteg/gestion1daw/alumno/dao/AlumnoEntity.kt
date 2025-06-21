package nicolasorteg.gestion1daw.alumno.dao

import nicolasorteg.gestion1daw.expediente.dao.ExpedienteEntity
import nicolasorteg.gestion1daw.modulo.dao.ModuloEntity

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