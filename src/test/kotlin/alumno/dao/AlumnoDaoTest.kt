/*package alumno.dao

import nicolasorteg.gestion1daw.alumno.dao.AlumnoDao
import nicolasorteg.gestion1daw.alumno.dao.AlumnoEntity
import nicolasorteg.gestion1daw.common.database.JdbiManager
import nicolasorteg.gestion1daw.expediente.dao.ExpedienteEntity
import nicolasorteg.gestion1daw.modulo.dao.ModuloEntity
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlumnoDaoTest {

    private lateinit var dao: AlumnoDao

    private val expedienteDummy = ExpedienteEntity(
        calificaciones = mapOf("Matemáticas" to 9.5, "Historia" to 8.0),
        notaMedia = 8.75,
        observaciones = "Buen expediente"
    )

    private val modulosDummy = listOf(
        ModuloEntity(nombre = "Matemáticas", siglas = "MAT", horas = 60),
        ModuloEntity(nombre = "Historia", siglas = "HIS", horas = 45)
    )

    private val alumnoEntity = AlumnoEntity(
        id = 0,
        nombre = "Nicolas",
        apellidos = "Ortega",
        fechaNacimiento = "2000-01-01",
        edad = 25,
        nacionalidad = "Española",
        fechaIncorporacion = "2022-09-01",
        modulos = modulosDummy,
        expediente = expedienteDummy,
        notaMedia = 8.5,
        faltas = 2,
        retrasos = 1,
        partes = 0
    )

    @BeforeAll
    fun setUp() {
        val jdbiManager = JdbiManager(
            databaseUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            databaseInitData = true,
            databaseInitTables = true,
            databaseLogger = true
        )
        dao = jdbiManager.jdbi.onDemand(AlumnoDao::class.java)
    }

    @AfterEach
    fun tearDown() {
        dao.deleteAll()
    }

    @Test
    fun testSaveAndFindById() {
        val id = dao.save(alumnoEntity)
        val saved = dao.findById(id)
        assertNotNull(saved)
        assertEquals(alumnoEntity.nombre, saved!!.nombre)
        assertEquals(alumnoEntity.apellidos, saved.apellidos)
        assertEquals(alumnoEntity.fechaNacimiento, saved.fechaNacimiento)
        assertEquals(alumnoEntity.edad, saved.edad)
        assertEquals(alumnoEntity.nacionalidad, saved.nacionalidad)
        assertEquals(alumnoEntity.fechaIncorporacion, saved.fechaIncorporacion)
        assertEquals(alumnoEntity.notaMedia, saved.notaMedia)
        assertEquals(alumnoEntity.faltas, saved.faltas)
        assertEquals(alumnoEntity.retrasos, saved.retrasos)
        assertEquals(alumnoEntity.partes, saved.partes)
    }

    @Test
    fun testUpdate() {
        val id = dao.save(alumnoEntity)
        val alumnoModificado = alumnoEntity.copy(
            id = id,
            nombre = "NombreModificado",
            notaMedia = 9.0
        )
        val filas = dao.update(alumnoModificado)
        assertEquals(1, filas)

        val actualizado = dao.findById(id)
        assertEquals("NombreModificado", actualizado?.nombre)
        assertEquals(9.0, actualizado?.notaMedia)
    }

    @Test
    fun testDelete() {
        val id = dao.save(alumnoEntity)
        val filas = dao.delete(id)
        assertEquals(1, filas)

        val alumno = dao.findById(id)
        assertNull(alumno)
    }

    @Test
    fun testFindAll() {
        dao.save(alumnoEntity)
        dao.save(alumnoEntity.copy(nombre = "Otro Nombre", id = 0))
        val todos = dao.findAll()
        assertTrue(todos.size >= 2)
    }

    @Test
    fun testDeleteAll() {
        dao.save(alumnoEntity)
        dao.save(alumnoEntity.copy(nombre = "Otro Nombre", id = 0))
        val filas = dao.deleteAll()
        assertTrue(filas >= 2)

        val todos = dao.findAll()
        assertTrue(todos.isEmpty())
    }
}*/
