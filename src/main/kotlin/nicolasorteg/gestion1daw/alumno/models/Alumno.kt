package nicolasorteg.gestion1daw.alumno.models

import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.modulo.model.Modulo

/**
 * Aquí se guardan los datos/atributos de Alumno, que hereda de Persona.
 */
class Alumno(

    id: Int,
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    edad: Int,
    nacionalidad: String,
    val fechaIncorporacion: String,
    val modulos: List<Modulo>,
    val expediente: Expediente,
    val notaMedia: Double,
    val faltas: Int,
    val retrasos: Int,
    val partes: Int,

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