package nicolasorteg.gestion1daw.alumno.models

/**
 * Aquí se almacenan los datos/atributos de Persona.
 */
abstract class Persona(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val edad: Int,
    val nacionalidad: String,
)