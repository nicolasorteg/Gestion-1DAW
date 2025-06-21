package nicolasorteg.gestion1daw.alumno.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import nicolasorteg.gestion1daw.alumno.error.PersonaError
import nicolasorteg.gestion1daw.alumno.models.Alumno
import org.lighthousegames.logging.logging

/**
 * Aquí se valida la entrada de datos del Alumno,
 * heredando de la Interfaz Validator.
 */
class AlumnoValidator: Validator<Alumno, PersonaError> {
    private val logger = logging()

    override fun validate(t: Alumno): Result<Alumno, PersonaError> {
        logger.debug { "🔵 Validando la entrada de datos del Alumno..." }

        if (t.nombre.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("El nombre no puede estar en blanco."))
        }
        if (t.nombre.length < 2 || t.nombre.length > 27){
            return Err(PersonaError.PersonaValidatorError("El nombre debe tener entre 2 y 27 caracteres."))
        }

        if (t.apellidos.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("El apellido no puede estar en blanco."))
        }
        if (t.apellidos.length < 2 || t.apellidos.length > 40){
            return Err(PersonaError.PersonaValidatorError("El apellido debe tener entre 2 y 40 caracteres."))
        }

        if (t.fechaNacimiento.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("La fecha de nacimiento no puede estar en blanco."))
        }

        if (t.edad < 12 || t.edad > 99) {
            return Err(PersonaError.PersonaValidatorError("La edad no es válida."))
        }

        if (t.nacionalidad.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("La nacionalidad no puede estar en blanco."))
        }

        if (t.fechaIncorporacion.isBlank()) {
            return Err(PersonaError.PersonaValidatorError("La fecha de incorporación no puede estar en blanco."))
        }

        if (t.notaMedia < 0.0 || t.notaMedia > 10.0) {
            return Err(PersonaError.PersonaValidatorError("La nota debe estar entre 0-10."))
        }

        if (t.faltas < 0) {
            return Err(PersonaError.PersonaValidatorError("No puede tener faltas de asistencia negativas."))
        }

        if (t.retrasos < 0) {
            return Err(PersonaError.PersonaValidatorError("No puede tener retrasos negativos."))
        }

        if (t.partes < 0) {
            return Err(PersonaError.PersonaValidatorError("No puede tener partes negativos."))
        }
        return Ok(t)
    }
}