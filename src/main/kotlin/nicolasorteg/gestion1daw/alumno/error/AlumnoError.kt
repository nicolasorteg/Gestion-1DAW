package nicolasorteg.gestion1daw.alumno.error


/**
 * Clase sellada que almacena todos los tipos de
 * errores que se podría dar en el programa respecto a Persona.
 */
sealed class AlumnoError(val message: String) {
    /**
     * Almacena el error que se mostrará en el validador.
     *
     * @param message Mensaje de error.
     */
    class AlumnoValidatorError(message: String): AlumnoError(message)

    /**
     * Almacena el error que se mostrará cuando no se encuentre el ID.
     *
     * @param id Identificador personal de la persona que no se ha podido encontrar.
     *
     */
    class AlumnoIdNotFound(id: Int): AlumnoError("Persona no encontrada con id: $id")

    /**
     * Almacena el error que se mostrará cuando el storage del programa no encuentre el archivo.
     *
     * @param message Mensaje de error.
     */
    class AlumnoStorageError(message: String): AlumnoError(message)

    /**
     *  Almacena el error que se mostrará cuando la base de datos de la persona no se haya conectado.
     *
     *  @param message Mensaje de error.
     */
    class AlumnoDatabaseException(message: String): AlumnoError(message)

    /**
     * Almacenaa el error que se mostrara en el servicio cuando haya algún error.
     *
     * @param message Mensaje de error.
     */

    class AlumnoServiceException(message: String): AlumnoError(message)
}