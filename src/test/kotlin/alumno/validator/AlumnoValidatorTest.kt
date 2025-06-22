package alumno.validator

import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.validator.AlumnoValidator
import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.modulo.model.Modulo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AlumnoValidatorTest {

    private val validator = AlumnoValidator()

    private fun getDefaultModulos() = listOf(Modulo("Programación", "PRO", 100))
    private fun getDefaultExpediente() = Expediente(
        calificaciones = mapOf("PRO" to 6.0),
        notaMedia = 6.0,
        observaciones = "Correcto"
    )

    @Test
    @DisplayName("Validación correcta del alumno")
    fun validateAlumnoCorrecto() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Española",
            fechaIncorporacion = "2020-01-01",
            modulos = getDefaultModulos(),
            expediente = getDefaultExpediente(),
            notaMedia = 7.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)
        assertTrue(result.isOk)
    }

    @Test
    @DisplayName("Nombre en blanco")
    fun validateNombreBlanco() {
        val alumno = createValidAlumno(nombre = "")
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("El nombre no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Nombre con menos de 2 caracteres")
    fun validateNombreMuyCorto() {
        val alumno = createValidAlumno(nombre = "A")
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("El nombre debe tener entre 2 y 27 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Nombre con más de 27 caracteres")
    fun validateNombreMuyLargo() {
        val alumno = createValidAlumno(nombre = "A".repeat(28))
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("El nombre debe tener entre 2 y 27 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Apellidos en blanco")
    fun validateApellidosBlanco() {
        val alumno = createValidAlumno(apellidos = "")
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("El apellido no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Apellidos con menos de 2 caracteres")
    fun validateApellidosMuyCortos() {
        val alumno = createValidAlumno(apellidos = "B")
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("El apellido debe tener entre 2 y 40 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Apellidos con más de 40 caracteres")
    fun validateApellidosMuyLargos() {
        val alumno = createValidAlumno(apellidos = "B".repeat(41))
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("El apellido debe tener entre 2 y 40 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Fecha de nacimiento en blanco")
    fun validateFechaNacimientoBlanca() {
        val alumno = createValidAlumno(fechaNacimiento = "")
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("La fecha de nacimiento no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Edad demasiado baja")
    fun validateEdadDemasiadoBaja() {
        val alumno = createValidAlumno(edad = 10)
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("La edad no es válida.", result.error.message)
    }

    @Test
    @DisplayName("Edad demasiado alta")
    fun validateEdadDemasiadoAlta() {
        val alumno = createValidAlumno(edad = 100)
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("La edad no es válida.", result.error.message)
    }

    @Test
    @DisplayName("Nacionalidad en blanco")
    fun validateNacionalidadBlanca() {
        val alumno = createValidAlumno(nacionalidad = "")
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("La nacionalidad no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Fecha de incorporación en blanco")
    fun validateFechaIncorporacionBlanca() {
        val alumno = createValidAlumno(fechaIncorporacion = "")
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("La fecha de incorporación no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Nota media demasiado alta")
    fun validateNotaMediaDemasiadoAlta() {
        val alumno = createValidAlumno(notaMedia = 11.0)
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("La nota debe estar entre 0-10.", result.error.message)
    }

    @Test
    @DisplayName("Nota media demasiado baja")
    fun validateNotaMediaDemasiadoBaja() {
        val alumno = createValidAlumno(notaMedia = -1.0)
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("La nota debe estar entre 0-10.", result.error.message)
    }

    @Test
    @DisplayName("Faltas negativas")
    fun validateFaltasNegativas() {
        val alumno = createValidAlumno(faltas = -1)
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("No puede tener faltas de asistencia negativas.", result.error.message)
    }

    @Test
    @DisplayName("Retrasos negativos")
    fun validateRetrasosNegativos() {
        val alumno = createValidAlumno(retrasos = -1)
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("No puede tener retrasos negativos.", result.error.message)
    }

    @Test
    @DisplayName("Partes negativos")
    fun validatePartesNegativos() {
        val alumno = createValidAlumno(partes = -1)
        val result = validator.validate(alumno)
        assertTrue(result.isErr)
        assertEquals("No puede tener partes negativos.", result.error.message)
    }

    private fun createValidAlumno(
        id: Int = 1,
        nombre: String = "Carlos",
        apellidos: String = "Alcaraz",
        fechaNacimiento: String = "2000-01-01",
        edad: Int = 18,
        nacionalidad: String = "Española",
        fechaIncorporacion: String = "2020-01-01",
        modulos: List<Modulo> = getDefaultModulos(),
        expediente: Expediente = getDefaultExpediente(),
        notaMedia: Double = 7.0,
        faltas: Int = 0,
        retrasos: Int = 0,
        partes: Int = 0,
    ) = Alumno(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fechaNacimiento = fechaNacimiento,
        edad = edad,
        nacionalidad = nacionalidad,
        fechaIncorporacion = fechaIncorporacion,
        modulos = modulos,
        expediente = expediente,
        notaMedia = notaMedia,
        faltas = faltas,
        retrasos = retrasos,
        partes = partes
    )
}
