package nicolasorteg.gestion1daw.alumno.models

/**
 * Aquí se almacenan los datos/atributos de Persona.
 */
abstract class Persona(
    val id: Int,
    var nombre: String,
    var apellidos: String,
    var fechaNacimiento: String,
    var edad: Int,
    var nacionalidad: String,
)