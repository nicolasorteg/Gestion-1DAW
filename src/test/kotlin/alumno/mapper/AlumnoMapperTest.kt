package alumno.mapper

import nicolasorteg.gestion1daw.alumno.dao.AlumnoEntity
import nicolasorteg.gestion1daw.alumno.dto.AlumnoDto
import nicolasorteg.gestion1daw.alumno.mappers.toDto
import nicolasorteg.gestion1daw.alumno.mappers.toEntity
import nicolasorteg.gestion1daw.alumno.mappers.toModel
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.expediente.dao.ExpedienteEntity
import nicolasorteg.gestion1daw.expediente.dto.ExpedienteDto
import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.modulo.dao.ModuloEntity
import nicolasorteg.gestion1daw.modulo.dto.ModuloDto
import nicolasorteg.gestion1daw.modulo.model.Modulo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AlumnoMapperTest {

    // datos de ejemplo para Modulo
    private val moduloModel = Modulo(nombre = "Programacion", siglas = "PROG", horas = 200)
    private val moduloDto = ModuloDto(nombre = "Programacion", siglas = "PROG", horas = 200)
    private val moduloEntity = ModuloEntity(nombre = "Programacion", siglas = "PROG", horas = 200)

    // datos de ejemplo para Expediente
    private val calificacionesEjemplo = mapOf("PROG" to 7.5, "BBDD" to 8.0)
    private val expedienteModel = Expediente(calificaciones = calificacionesEjemplo, notaMedia = 7.75, observaciones = "Buen progreso")
    private val expedienteDto = ExpedienteDto(calificaciones = calificacionesEjemplo, notaMedia = 7.75, observaciones = "Buen progreso")
    private val expedienteEntity = ExpedienteEntity(calificaciones = calificacionesEjemplo, notaMedia = 7.75, observaciones = "Buen progreso")

    // datos de ejemplo para Alumno
    private val alumnoModel = Alumno(
        id = 1,
        nombre = "Juan",
        apellidos = "Perez",
        fechaNacimiento = "2000-01-15",
        edad = 24,
        nacionalidad = "Española",
        fechaIncorporacion = "2023-09-01",
        modulos = listOf(moduloModel),
        expediente = expedienteModel,
        notaMedia = 7.75,
        faltas = 2,
        retrasos = 1,
        partes = 0
    )

    private val alumnoDto = AlumnoDto(
        id = 1,
        nombre = "Juan",
        apellidos = "Perez",
        fechaNacimiento = "2000-01-15",
        edad = 24,
        nacionalidad = "Española",
        fechaIncorporacion = "2023-09-01",
        modulos = listOf(moduloDto),
        expediente = expedienteDto,
        faltas = 2,
        retrasos = 1,
        partes = 0
    )

    private val alumnoEntity = AlumnoEntity(
        id = 1,
        nombre = "Juan",
        apellidos = "Perez",
        fechaNacimiento = "2000-01-15",
        edad = 24,
        nacionalidad = "Española",
        fechaIncorporacion = "2023-09-01",
        modulos = listOf(moduloEntity),
        expediente = expedienteEntity,
        notaMedia = 7.75,
        faltas = 2,
        retrasos = 1,
        partes = 0
    )

    @Test
    fun alumnoToDto() {
        val result = alumnoModel.toDto()
        assertNotNull(result)
        assertEquals(alumnoDto, result)
        assertTrue(result is AlumnoDto, "El resultado debe ser de tipo AlumnoDto")
    }

    @Test
    fun alumnoEntityToDto() {
        val result = alumnoEntity.toDto()
        assertNotNull(result)
        assertEquals(alumnoDto, result)
        assertTrue(result is AlumnoDto, "El resultado debe ser de tipo AlumnoDto")
    }

    @Test
    fun alumnoDtoToModel() {
        val result = alumnoDto.toModel()
        assertNotNull(result)
        // Comparación propiedad por propiedad
        assertEquals(alumnoModel.id, result.id)
        assertEquals(alumnoModel.nombre, result.nombre)
        assertEquals(alumnoModel.apellidos, result.apellidos)
        assertEquals(alumnoModel.fechaNacimiento, result.fechaNacimiento)
        assertEquals(alumnoModel.edad, result.edad)
        assertEquals(alumnoModel.nacionalidad, result.nacionalidad)
        assertEquals(alumnoModel.fechaIncorporacion, result.fechaIncorporacion)
        assertEquals(alumnoModel.modulos.size, result.modulos.size)
        alumnoModel.modulos.forEachIndexed { index, expectedModulo ->
            val actualModulo = result.modulos[index]
            assertEquals(expectedModulo.nombre, actualModulo.nombre)
            assertEquals(expectedModulo.siglas, actualModulo.siglas)
            assertEquals(expectedModulo.horas, actualModulo.horas)
        }
        assertEquals(alumnoModel.expediente.calificaciones, result.expediente.calificaciones)
        assertEquals(alumnoModel.expediente.notaMedia, result.expediente.notaMedia)
        assertEquals(alumnoModel.expediente.observaciones, result.expediente.observaciones)

        assertEquals(alumnoModel.notaMedia, result.notaMedia)
        assertEquals(alumnoModel.faltas, result.faltas)
        assertEquals(alumnoModel.retrasos, result.retrasos)
        assertEquals(alumnoModel.partes, result.partes)
        assertTrue(result is Alumno, "El resultado debe ser de tipo Alumno")
    }

    @Test
    fun alumnoEntityToModel() {
        val result = alumnoEntity.toModel()
        assertNotNull(result)
        // Comparación propiedad por propiedad
        assertEquals(alumnoModel.id, result.id)
        assertEquals(alumnoModel.nombre, result.nombre)
        assertEquals(alumnoModel.apellidos, result.apellidos)
        assertEquals(alumnoModel.fechaNacimiento, result.fechaNacimiento)
        assertEquals(alumnoModel.edad, result.edad)
        assertEquals(alumnoModel.nacionalidad, result.nacionalidad)
        assertEquals(alumnoModel.fechaIncorporacion, result.fechaIncorporacion)
        assertEquals(alumnoModel.modulos.size, result.modulos.size)
        alumnoModel.modulos.forEachIndexed { index, expectedModulo ->
            val actualModulo = result.modulos[index]
            assertEquals(expectedModulo.nombre, actualModulo.nombre)
            assertEquals(expectedModulo.siglas, actualModulo.siglas)
            assertEquals(expectedModulo.horas, actualModulo.horas)
        }
        assertEquals(alumnoModel.expediente.calificaciones, result.expediente.calificaciones)
        assertEquals(alumnoModel.expediente.notaMedia, result.expediente.notaMedia)
        assertEquals(alumnoModel.expediente.observaciones, result.expediente.observaciones)

        assertEquals(alumnoModel.notaMedia, result.notaMedia)
        assertEquals(alumnoModel.faltas, result.faltas)
        assertEquals(alumnoModel.retrasos, result.retrasos)
        assertEquals(alumnoModel.partes, result.partes)
        assertTrue(result is Alumno, "El resultado debe ser de tipo Alumno")
    }

    @Test
    fun alumnoToEntity() {
        val result = alumnoModel.toEntity()
        assertNotNull(result)
        assertEquals(alumnoEntity, result)
        assertTrue(result is AlumnoEntity, "El resultado debe ser de tipo AlumnoEntity")
    }

    @Test
    fun AlumnoDtoToEntity() {
        val result = alumnoDto.toEntity()
        assertNotNull(result)
        assertEquals(alumnoEntity, result)
        assertTrue(result is AlumnoEntity, "El resultado debe ser de tipo AlumnoEntity")
    }
}