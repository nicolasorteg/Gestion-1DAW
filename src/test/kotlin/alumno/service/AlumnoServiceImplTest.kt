package alumno.service

import com.github.michaelbull.result.*
import nicolasorteg.gestion1daw.alumno.error.AlumnoError
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.repository.AlumnoRepositoryImpl
import nicolasorteg.gestion1daw.alumno.service.AlumnoServiceImpl
import nicolasorteg.gestion1daw.alumno.storage.AlumnoStorageCsv
import nicolasorteg.gestion1daw.alumno.validator.AlumnoValidator
import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.modulo.model.Modulo
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.io.File
import org.junit.jupiter.api.Assertions.*

@ExtendWith(MockitoExtension::class)
class AlumnoServiceImplTest {

    @Mock
    private lateinit var repository: AlumnoRepositoryImpl
    @Mock
    private lateinit var validator: AlumnoValidator
    @Mock
    private lateinit var storage: AlumnoStorageCsv

    private lateinit var service: AlumnoServiceImpl

    private val expediente = Expediente(emptyMap(), 0.0, "")
    private val modulos = emptyList<Modulo>()

    private val alumno = Alumno(
        id = 1,
        nombre = "Juan",
        apellidos = "Pérez",
        fechaNacimiento = "2000-01-01",
        edad = 20,
        nacionalidad = "Española",
        fechaIncorporacion = "2024-09-01",
        modulos = modulos,
        expediente = expediente,
        notaMedia = 8.5,
        faltas = 0,
        retrasos = 0,
        partes = 0
    )

    private val invalidAlumno = Alumno(
        id = 1,
        nombre = "",
        apellidos = "Pérez",
        fechaNacimiento = "2000-01-01",
        edad = 20,
        nacionalidad = "Española",
        fechaIncorporacion = "2024-09-01",
        modulos = modulos,
        expediente = expediente,
        notaMedia = 8.5,
        faltas = 0,
        retrasos = 0,
        partes = 0
    )

    @BeforeEach
    fun inicializarServicioConMocks() {
        service = AlumnoServiceImpl(repository, validator, storage)
    }

    @Nested
    inner class CasosCorrectos {

        @Test
        fun testGetAllDevuelveListaDeAlumnos() {
            whenever(repository.getAll()).thenReturn(listOf(alumno))

            val result = service.getAll()

            assertTrue(result.isOk)
            assertEquals(1, result.value.size)
            assertEquals(alumno, result.value[0])
            verify(repository, times(1)).getAll()
        }

        @Test
        fun testGetByIDDevuelveAlumnoExistente() {
            whenever(repository.getById(1)).thenReturn(alumno)

            val result = service.getByID(1)

            assertTrue(result.isOk)
            assertEquals(alumno, result.value)
            verify(repository, times(1)).getById(1)
        }

        @Test
        fun testSaveAlumnoValidoDevuelveAlumnoGuardado() {
            whenever(validator.validate(alumno)).thenReturn(Ok(alumno))
            whenever(repository.save(alumno)).thenReturn(alumno)

            val result = service.save(alumno)

            assertTrue(result.isOk)
            assertEquals(alumno, result.value)
            verify(validator, times(1)).validate(alumno)
            verify(repository, times(1)).save(alumno)
        }

        @Test
        fun testUpdateAlumnoExistenteDevuelveAlumnoActualizado() {
            whenever(validator.validate(alumno)).thenReturn(Ok(alumno))
            whenever(repository.update(alumno, 1)).thenReturn(alumno)

            val result = service.update(1, alumno)

            assertTrue(result.isOk)
            assertEquals(alumno, result.value)
            verify(validator, times(1)).validate(alumno)
            verify(repository, times(1)).update(alumno, 1)
        }

        @Test
        fun testDeleteAlumnoExistenteDevuelveAlumnoEliminado() {
            whenever(repository.delete(1)).thenReturn(alumno)

            val result = service.delete(1)

            assertTrue(result.isOk)
            assertEquals(alumno, result.value)
            verify(repository, times(1)).delete(1)
        }

        @Test
        fun testLoadFromCsvCargaAlumnosCorrectamente() {
            val dummyFile = File("alumnos.csv")

            whenever(storage.readFromFile(any())).thenReturn(Ok(listOf(alumno)))

            val result = service.loadFromCsv(dummyFile)

            assertTrue(result.isOk)
            assertEquals(listOf(alumno), result.value)
            verify(storage, times(1)).readFromFile(any())
        }

        @Nested
        inner class CasosDeError {

            @Test
            fun testGetByIDDevuelveErrorSiAlumnoNoExiste() {
                whenever(repository.getById(1)).thenReturn(null)

                val result = service.getByID(1)

                assertTrue(result.isErr)
                assertTrue(result.error is AlumnoError.AlumnoServiceException)
                verify(repository, times(1)).getById(1)
            }

            @Test
            fun testSaveDevuelveErrorSiAlumnoNoValido() {
                whenever(validator.validate(invalidAlumno)).thenReturn(Err(AlumnoError.AlumnoServiceException("Nombre vacío")))

                val result = service.save(invalidAlumno)

                assertTrue(result.isErr)
                assertTrue(result.error is AlumnoError.AlumnoServiceException)
                verify(validator, times(1)).validate(invalidAlumno)
                verify(repository, never()).save(any())
            }

            @Test
            fun testSaveDevuelveErrorSiRepositorioLanzaExcepcion() {
                whenever(validator.validate(alumno)).thenReturn(Ok(alumno))
                doThrow(RuntimeException("Error al guardar")).whenever(repository).save(alumno)

                val result = service.save(alumno)

                assertTrue(result.isErr)
                assertTrue(result.error is AlumnoError.AlumnoServiceException)
                verify(validator, times(1)).validate(alumno)
                verify(repository, times(1)).save(alumno)
            }

            @Test
            fun testUpdateDevuelveErrorSiAlumnoNoEncontrado() {
                whenever(validator.validate(alumno)).thenReturn(Ok(alumno))
                whenever(repository.update(alumno, 1)).thenReturn(null)

                val result = service.update(1, alumno)

                assertTrue(result.isErr)
                assertTrue(result.error is AlumnoError.AlumnoServiceException)
                verify(validator, times(1)).validate(alumno)
                verify(repository, times(1)).update(alumno, 1)
            }

            @Test
            fun testDeleteDevuelveErrorSiAlumnoNoEncontrado() {
                whenever(repository.delete(1)).thenReturn(null)

                val result = service.delete(1)

                assertTrue(result.isErr)
                assertTrue(result.error is AlumnoError.AlumnoServiceException)
                verify(repository, times(1)).delete(1)
            }

            @Test
            fun testDeleteDevuelveErrorSiRepositorioLanzaExcepcion() {
                doThrow(RuntimeException("Error grave")).whenever(repository).delete(1)

                val result = service.delete(1)

                assertTrue(result.isErr)
                assertTrue(result.error is AlumnoError.AlumnoServiceException)
                verify(repository, times(1)).delete(1)
            }
        }
    }
}
