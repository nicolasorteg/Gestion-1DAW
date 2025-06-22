package nicolasorteg.gestion1daw.alumno.mapper

import nicolasorteg.gestion1daw.alumno.dao.AlumnoEntity
import nicolasorteg.gestion1daw.alumno.dto.AlumnoDto
import nicolasorteg.gestion1daw.alumno.mappers.toDto
import nicolasorteg.gestion1daw.alumno.mappers.toEntity
import nicolasorteg.gestion1daw.alumno.mappers.toModel
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.modulo.dao.ModuloEntity
import nicolasorteg.gestion1daw.modulo.dto.ModuloDto
import nicolasorteg.gestion1daw.modulo.model.Modulo
import nicolasorteg.gestion1daw.expediente.dao.ExpedienteEntity
import nicolasorteg.gestion1daw.expediente.dto.ExpedienteDto
import nicolasorteg.gestion1daw.expediente.model.Expediente
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AlumnoMapperTest {

    // Datos de ejemplo para Modulo
    private val moduloEntity = ModuloEntity("Matemáticas", "MAT", 100)
    private val moduloDto = ModuloDto("Matemáticas", "MAT", 100)
    private val moduloModel = Modulo("Matemáticas", "MAT", 100)

    // Datos de ejemplo para Expediente
    private val expedienteEntity = ExpedienteEntity(mapOf("MAT" to 7.5), 7.5, "Observación")
    private val expedienteDto = ExpedienteDto(mapOf("MAT" to 7.5), 7.5, "Observación")
    private val expedienteModel = Expediente(mapOf("MAT" to 7.5), 7.5, "Observación")

    // Datos de ejemplo para Alumno
    private val alumnoEntity = AlumnoEntity(
        id = 1,
        nombre = "Nico",
        apellidos = "Ortega",
        fechaNacimiento = "2000-01-01",
        edad = 25,
        nacionalidad = "Española",
        fechaIncorporacion = "2018-09-01",
        modulos = listOf(moduloEntity),
        expediente = expedienteEntity,
        notaMedia = 7.5,
        faltas = 2,
        retrasos = 1,
        partes = 0
    )

    private val alumnoDto = AlumnoDto(
        id = 1,
        nombre = "Nico",
        apellidos = "Ortega",
        fechaNacimiento = "2000-01-01",
        edad = 25,
        nacionalidad = "Española",
        fechaIncorporacion = "2018-09-01",
        modulos = listOf(moduloDto),
        expediente = expedienteDto,
        notaMedia = 7.5,
        faltas = 2,
        retrasos = 1,
        partes = 0
    )

    private val alumnoModel = Alumno(
        id = 1,
        nombre = "Nico",
        apellidos = "Ortega",
        fechaNacimiento = "2000-01-01",
        edad = 25,
        nacionalidad = "Española",
        fechaIncorporacion = "2018-09-01",
        modulos = listOf(moduloModel),
        expediente = expedienteModel,
        notaMedia = 7.5,
        faltas = 2,
        retrasos = 1,
        partes = 0
    )

    private fun assertAlumnoEquals(expected: Alumno, actual: Alumno) {
        assertEquals(expected.id, actual.id, "ID no coincide")
        assertEquals(expected.nombre, actual.nombre, "Nombre no coincide")
        assertEquals(expected.apellidos, actual.apellidos, "Apellidos no coinciden")
        assertEquals(expected.fechaNacimiento, actual.fechaNacimiento, "Fecha de Nacimiento no coincide")
        assertEquals(expected.edad, actual.edad, "Edad no coincide")
        assertEquals(expected.nacionalidad, actual.nacionalidad, "Nacionalidad no coincide")
        assertEquals(expected.fechaIncorporacion, actual.fechaIncorporacion, "Fecha de Incorporación no coincide")
        assertEquals(expected.notaMedia, actual.notaMedia, "Nota Media no coincide")
        assertEquals(expected.faltas, actual.faltas, "Faltas no coinciden")
        assertEquals(expected.retrasos, actual.retrasos, "Retrasos no coinciden")
        assertEquals(expected.partes, actual.partes, "Partes no coinciden")


        assertEquals(expected.modulos.size, actual.modulos.size, "El número de módulos no coincide")
        expected.modulos.forEachIndexed { index, expectedModulo ->
            val actualModulo = actual.modulos[index]
            assertEquals(expectedModulo.nombre, actualModulo.nombre, "Nombre de módulo en el índice $index no coincide")
            assertEquals(expectedModulo.siglas, actualModulo.siglas, "Siglas de módulo en el índice $index no coinciden")
            assertEquals(expectedModulo.horas, actualModulo.horas, "Horas de módulo en el índice $index no coinciden")
        }


        assertEquals(expected.expediente.calificaciones, actual.expediente.calificaciones, "Calificaciones del expediente no coinciden")
        assertEquals(expected.expediente.notaMedia, actual.expediente.notaMedia, "Nota media del expediente no coincide")
        assertEquals(expected.expediente.observaciones, actual.expediente.observaciones, "Observaciones del expediente no coinciden")
    }



    @Test
    fun `AlumnoEntity toDto y back`() {
        val dto = alumnoEntity.toDto()

        assertEquals(alumnoDto, dto, "La conversión de AlumnoEntity a AlumnoDto no es correcta.")

        val entityBack = dto.toEntity()

        assertEquals(alumnoEntity, entityBack, "La conversión de AlumnoDto a AlumnoEntity no es correcta.")
    }



    @Test
    fun `AlumnoDto toModel y back`() {
        val model = alumnoDto.toModel()

        assertAlumnoEquals(alumnoModel, model)

        val dtoBack = model.toDto()

        assertEquals(alumnoDto, dtoBack, "La conversión de Alumno (model) a AlumnoDto no es correcta.")
    }


    @Test
    fun `AlumnoEntity toModel y back`() {
        val model = alumnoEntity.toModel()

        assertAlumnoEquals(alumnoModel, model)

        val entityBack = model.toEntity()

        assertEquals(alumnoEntity, entityBack, "La conversión de Alumno (model) a AlumnoEntity no es correcta.")
    }
}