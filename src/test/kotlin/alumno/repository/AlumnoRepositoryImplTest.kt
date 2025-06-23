package alumno.repository

import nicolasorteg.gestion1daw.alumno.dao.AlumnoDao
import nicolasorteg.gestion1daw.alumno.dao.AlumnoEntity
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.repository.AlumnoRepositoryImpl
import nicolasorteg.gestion1daw.expediente.dao.ExpedienteEntity
import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.modulo.dao.ModuloEntity
import nicolasorteg.gestion1daw.modulo.model.Modulo
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any

@ExtendWith(MockitoExtension::class)
class AlumnoRepositoryImplTest {

    @Mock
    lateinit var dao: AlumnoDao

    lateinit var repository: AlumnoRepositoryImpl

    private lateinit var alumno: Alumno
    private lateinit var alumnoEntity: AlumnoEntity

    @BeforeEach
    fun setupDatosIniciales() {
        repository = AlumnoRepositoryImpl(dao)

        alumno = Alumno(
            id = 1,
            nombre = "Nico",
            apellidos = "Ortega",
            fechaNacimiento = "2000-01-01",
            edad = 24,
            nacionalidad = "España",
            fechaIncorporacion = "2023-09-01",
            modulos = listOf(
                Modulo("Programación", "PRO", 256),
                Modulo("Bases de Datos", "BDD", 192)
            ),
            expediente = Expediente(
                calificaciones = mapOf("Programación" to 8.0, "BDD" to 9.0),
                notaMedia = 8.5,
                observaciones = "Buen rendimiento"
            ),
            notaMedia = 8.5,
            faltas = 2,
            retrasos = 1,
            partes = 0
        )

        alumnoEntity = AlumnoEntity(
            id = 1,
            nombre = "Nico",
            apellidos = "Ortega",
            fechaNacimiento = "2000-01-01",
            edad = 24,
            nacionalidad = "España",
            fechaIncorporacion = "2023-09-01",
            modulos = listOf(
                ModuloEntity("Programación", "PRO", 256),
                ModuloEntity("Bases de Datos", "BDD", 192)
            ),
            expediente = ExpedienteEntity(
                calificaciones = mapOf("Programación" to 8.0, "BDD" to 9.0),
                notaMedia = 8.5,
                observaciones = "Buen rendimiento"
            ),
            notaMedia = 8.5,
            faltas = 2,
            retrasos = 1,
            partes = 0
        )
    }

    @Test
    @DisplayName("Test que verifica guardar un alumno correctamente")
    fun testGuardarAlumnoCorrectamente() {
        `when`(dao.save(any())).thenReturn(1)
        `when`(dao.findById(1)).thenReturn(alumnoEntity)

        val result = repository.save(alumno)

        Assertions.assertEquals(alumno.nombre, result.nombre)
        verify(dao, times(1)).save(any())
        verify(dao, times(1)).findById(1)
    }

    @Test
    @DisplayName("Test que verifica obtener todos los alumnos")
    fun testObtenerTodosLosAlumnos() {
        `when`(dao.findAll()).thenReturn(listOf(alumnoEntity))

        val result = repository.getAll()

        Assertions.assertEquals(1, result.size)
        Assertions.assertEquals("Nico", result.first().nombre)
        verify(dao, times(1)).findAll()
    }

    @Test
    @DisplayName("Test que verifica obtener un alumno por ID cuando existe")
    fun testObtenerAlumnoPorIdExistente() {
        `when`(dao.findById(1)).thenReturn(alumnoEntity)

        val result = repository.getById(1)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("Nico", result!!.nombre)
        verify(dao, times(1)).findById(1)
    }

    @Test
    @DisplayName("Test que devuelve null al obtener un alumno por ID inexistente")
    fun testObtenerAlumnoPorIdNoExistente() {
        `when`(dao.findById(99)).thenReturn(null)

        val result = repository.getById(99)

        Assertions.assertNull(result)
        verify(dao, times(1)).findById(99)
    }

    @Test
    @DisplayName("Test que elimina un alumno existente correctamente")
    fun testEliminarAlumnoExistente() {
        `when`(dao.findById(1)).thenReturn(alumnoEntity)
        `when`(dao.delete(1)).thenReturn(1)

        val result = repository.delete(1)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("Nico", result!!.nombre)
        verify(dao, times(1)).findById(1)
        verify(dao, times(1)).delete(1)
    }

    @Test
    @DisplayName("Test que no elimina si el alumno no existe")
    fun testNoEliminarAlumnoNoExistente() {
        `when`(dao.findById(2)).thenReturn(null)

        val result = repository.delete(2)

        Assertions.assertNull(result)
        verify(dao, times(1)).findById(2)
        verify(dao, times(0)).delete(any())
    }

    @Test
    @DisplayName("Test que actualiza un alumno existente correctamente")
    fun testActualizarAlumnoExistente() {
        val updatedAlumno = Alumno(
            id = alumno.id,
            nombre = "Actualizado",
            apellidos = alumno.apellidos,
            fechaNacimiento = alumno.fechaNacimiento,
            edad = alumno.edad,
            nacionalidad = alumno.nacionalidad,
            fechaIncorporacion = alumno.fechaIncorporacion,
            modulos = alumno.modulos,
            expediente = alumno.expediente,
            notaMedia = alumno.notaMedia,
            faltas = alumno.faltas,
            retrasos = alumno.retrasos,
            partes = alumno.partes
        )

        val updatedEntity = AlumnoEntity(
            id = alumnoEntity.id,
            nombre = "Actualizado",
            apellidos = alumnoEntity.apellidos,
            fechaNacimiento = alumnoEntity.fechaNacimiento,
            edad = alumnoEntity.edad,
            nacionalidad = alumnoEntity.nacionalidad,
            fechaIncorporacion = alumnoEntity.fechaIncorporacion,
            modulos = alumnoEntity.modulos,
            expediente = alumnoEntity.expediente,
            notaMedia = alumnoEntity.notaMedia,
            faltas = alumnoEntity.faltas,
            retrasos = alumnoEntity.retrasos,
            partes = alumnoEntity.partes
        )

        `when`(dao.findById(1)).thenReturn(alumnoEntity).thenReturn(updatedEntity)
        `when`(dao.update(any())).thenReturn(1)

        val result = repository.update(updatedAlumno, 1)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("Actualizado", result!!.nombre)
        verify(dao, times(1)).update(any())
        verify(dao, times(2)).findById(1)
    }

    @Test
    @DisplayName("Test que no actualiza si el alumno no existe")
    fun testNoActualizarAlumnoNoExistente() {
        val updatedAlumno = Alumno(
            id = alumno.id,
            nombre = "Actualizado",
            apellidos = alumno.apellidos,
            fechaNacimiento = alumno.fechaNacimiento,
            edad = alumno.edad,
            nacionalidad = alumno.nacionalidad,
            fechaIncorporacion = alumno.fechaIncorporacion,
            modulos = alumno.modulos,
            expediente = alumno.expediente,
            notaMedia = alumno.notaMedia,
            faltas = alumno.faltas,
            retrasos = alumno.retrasos,
            partes = alumno.partes
        )

        `when`(dao.findById(99)).thenReturn(null)

        val result = repository.update(updatedAlumno, 99)

        Assertions.assertNull(result)
        verify(dao, times(1)).findById(99)
        verify(dao, times(0)).update(any())
    }

    @Test
    @DisplayName("Test que lanza excepción si no se puede recuperar el alumno después de guardar")
    fun testLanzarExcepcionSiNoSeRecuperaAlumnoDespuesDeGuardar() {
        `when`(dao.save(any())).thenReturn(1)
        `when`(dao.findById(1)).thenReturn(null)

        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            repository.save(alumno)
        }

        Assertions.assertEquals("Error al guardar el alumno.", exception.message)
        verify(dao, times(1)).save(any())
        verify(dao, times(1)).findById(1)
    }

    @Test
    @DisplayName("Test que devuelve null si la actualización no afecta a ninguna fila")
    fun testActualizarDevuelveNullSiNoAfectaFilas() {
        `when`(dao.findById(1)).thenReturn(alumnoEntity)
        `when`(dao.update(any())).thenReturn(0)

        val result = repository.update(alumno, 1)

        Assertions.assertNull(result)
        verify(dao, times(1)).findById(1)
        verify(dao, times(1)).update(any())
    }
}
