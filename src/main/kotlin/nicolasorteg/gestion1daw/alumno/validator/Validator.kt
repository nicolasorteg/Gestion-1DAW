package nicolasorteg.gestion1daw.alumno.validator

import com.github.michaelbull.result.Result

/**
 * Aquí se almacena el método que sirve para validar la entrada de datos.
 * Al ser una interfaz, actúa a modo de contrato y obliga a todas las clases
 * que hereden de esta a implementar la función "validate".
 */
interface Validator<T, E> {
    fun validate (t: T): Result<T, E>
}
