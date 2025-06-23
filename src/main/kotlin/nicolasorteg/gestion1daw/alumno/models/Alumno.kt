package nicolasorteg.gestion1daw.alumno.models

import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.modulo.model.Modulo

/**
 * Aquí se guardan los datos/atributos de Alumno, que hereda de Persona.
 *
 * @property fechaIncorporacion Fecha de incorporación del alumno.
 * @property modulos Lista de módulos que el alumno está cursando.
 * @property expediente Expediente académico del alumno.
 * @property notaMedia Nota media de las calificaciones del alumno.
 * @property faltas Número de faltas acumuladas por el alumno.
 * @property retrasos Número de retrasos acumulados por el alumno.
 * @property partes Número de partes disciplinarios recibidos.
 */
class Alumno(

    id: Int,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    edad: Int,
    nacionalidad: String,
    var fechaIncorporacion: String,
    val modulos: List<Modulo>,
    val expediente: Expediente,
    var notaMedia: Double,
    var faltas: Int,
    var retrasos: Int,
    var partes: Int,

    ): Persona(
    id,
    nombre,
    apellidos,
    fechaNacimiento,
    edad,
    nacionalidad
) {

    // CONCAT
    val nombreCompleto: String
        get() = "$nombre $apellidos"

    override fun toString(): String {
        return ("Alumno(id=$id, nombre=$nombre, apellidso=$apellidos, edad=$edad, nacionalidad=$nacionalidad, fechaIncoporacion=$fechaIncorporacion, modulos=$modulos, notaMedia=$notaMedia, faltas=$faltas, retrasos=$retrasos, partes=$partes)" )
    }
}