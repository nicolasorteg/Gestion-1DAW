package alumno.validator

import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.validator.AlumnoValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AlumnoValidatorTest {
    private val validator = AlumnoValidator()

    @Test
    @DisplayName("Test de Alumno Válido.")
    fun validateJugadorValido() {
        val alumno = Alumno (
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "1970-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2000-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isOk, "El validador debe devolver Ok")
        assertEquals(alumno, result.value, "El validador debe devolver el mismo alumno.")
    }

    @Test
    @DisplayName("Test de Alumno Nombre vacío.")
    fun validateJugadorNombreVacio() {
        val alumno = Alumno (
            id = 1,
            nombre = "",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("El nombre no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Nombre corto.")
    fun validateJugadorNombreCorto() {
        val alumno = Alumno (
            id = 1,
            nombre = "C",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("El nombre debe tener entre 2 y 27 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Nombre largo.")
    fun validateJugadorNombreLargo() {
        val alumno = Alumno (
            id = 1,
            nombre = "CarlosCarlosCarlosCarlosCarlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("El nombre debe tener entre 2 y 27 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Apellido vacío.")
    fun validateJugadorApellidoVacio() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("El apellido no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Apellido corto.")
    fun validateJugadorApellidoCorto() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "A",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("El apellido debe tener entre 2 y 40 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Apellido largo.")
    fun validateJugadorApellidoLargo() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "AlcarazAlcarazAlcarazAlcarazAlcarazAlcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("El apellido debe tener entre 2 y 40 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Fecha Nacimiento vacía.")
    fun validateJugadorFechaNacimientoVacia() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("La fecha de nacimiento no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Edad menor.")
    fun validateJugadorEdadMenor() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 10,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("La edad no es válida.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Edad mayor.")
    fun validateJugadorEdadMayor() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 100,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("La edad no es válida.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Nacionalidad vacía.")
    fun validateJugadorNacionalidadVacia() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("La nacionalidad no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Fecha Incorporación vacía.")
    fun validateJugadorFechaIncorporacionVacia() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "",
            modulos = "Programación y Entornos",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("La fecha de incorporación no puede estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Módulos vacíos.")
    fun validateJugadorModulosVacios() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("Los módulos no pueden estar en blanco.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Nota media superior.")
    fun validateJugadorNotaMediaSuperior() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación",
            notaMedia = 11.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("La nota debe estar entre 0-10.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno Nota media inferior.")
    fun validateJugadorNotaMediaInferior() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación",
            notaMedia = -1.0,
            faltas = 0,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("La nota debe estar entre 0-10.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno con faltas negativas.")
    fun validateJugadorFaltasNegativas() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación",
            notaMedia = 5.0,
            faltas = -1,
            retrasos = 0,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("No puede tener faltas de asistencia negativas.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno con retrasos negativos.")
    fun validateJugadorRetrasosNegativos() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2020-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2000-01-01",
            modulos = "Programación",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = -1,
            partes = 0,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("No puede tener retrasos negativos.", result.error.message)
    }

    @Test
    @DisplayName("Test de Alumno con partes negativos.")
    fun validateJugadorPartesNegativos() {
        val alumno = Alumno(
            id = 1,
            nombre = "Carlos",
            apellidos = "Alcaraz",
            fechaNacimiento = "2000-01-01",
            edad = 18,
            nacionalidad = "Español",
            fechaIncorporacion = "2020-01-01",
            modulos = "Programación",
            notaMedia = 5.0,
            faltas = 0,
            retrasos = 0,
            partes = -1,
        )

        val result = validator.validate(alumno)

        assertTrue(result.isErr)
        assertEquals("No puede tener partes negativos.", result.error.message)
    }

}