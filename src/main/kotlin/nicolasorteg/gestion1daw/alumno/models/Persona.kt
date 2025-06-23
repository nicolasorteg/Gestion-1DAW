package nicolasorteg.gestion1daw.alumno.models

/**
 * Aquí se almacenan los datos/atributos de Persona.
 *
 * @property id Identificador único de la persona.
 * @property nombre Nombre de la persona.
 * @property apellidos Apellidos de la persona.
 * @property fechaNacimiento Fecha de nacimiento de la persona en formato String.
 * @property edad Edad actual de la persona.
 * @property nacionalidad Nacionalidad de la persona.
 */
abstract class Persona(
    val id: Int,
    var nombre: String,
    var apellidos: String,
    var fechaNacimiento: String,
    var edad: Int,
    var nacionalidad: String,
)