package expediente.mapper

import nicolasorteg.gestion1daw.expediente.dao.ExpedienteEntity
import nicolasorteg.gestion1daw.expediente.dto.ExpedienteDto
import nicolasorteg.gestion1daw.expediente.mapper.toDto
import nicolasorteg.gestion1daw.expediente.mapper.toEntity
import nicolasorteg.gestion1daw.expediente.mapper.toModel
import nicolasorteg.gestion1daw.expediente.model.Expediente
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ExpedienteMapperTest {

    private val calificacionesEjemplo = mapOf("PROG" to 7.5, "BBDD" to 8.0, "SI" to 6.0)

    // datos de ejemplo para expediente
    private val expedienteModel = Expediente(
        calificaciones = calificacionesEjemplo,
        notaMedia = 7.166666666666667,
        observaciones = "Alumno con buen rendimiento general."
    )
    // datos de ejemplo para expedientedto
    private val expedienteDto = ExpedienteDto(
        calificaciones = calificacionesEjemplo,
        notaMedia = 7.166666666666667,
        observaciones = "Alumno con buen rendimiento general."
    )
    // datos de ejemplo para expedienteentity
    private val expedienteEntity = ExpedienteEntity(
        calificaciones = calificacionesEjemplo,
        notaMedia = 7.166666666666667,
        observaciones = "Alumno con buen rendimiento general."
    )

    @Test
    @DisplayName("Test que mapea de Expediente Modelo -> Expediente Dto")
    fun expedienteToDto() {
        val result = expedienteModel.toDto()
        assertNotNull(result)
        assertEquals(expedienteDto, result)
        assertTrue(result is ExpedienteDto, "El resultado debe ser de tipo ExpedienteDto")
    }

    @Test
    @DisplayName("Test que mapea de Expediente Entity -> Expediente Dto")
    fun expedienteEntityToDto() {
        val result = expedienteEntity.toDto()
        assertNotNull(result)
        assertEquals(expedienteDto, result)
        assertTrue(result is ExpedienteDto, "El resultado debe ser de tipo ExpedienteDto")
    }

    @Test
    @DisplayName("Test que mapea de Expediente Dto -> Expediente Modelo")
    fun expedienteDtoToModel() {
        val result = expedienteDto.toModel()
        assertNotNull(result)
        assertEquals(expedienteModel, result)
        assertTrue(result is Expediente, "El resultado debe ser de tipo Expediente")
    }

    @Test
    @DisplayName("Test que mapea de Expediente Entity -> Expediente Modelo")
    fun expedienteEntityToModel() {
        val result = expedienteEntity.toModel()
        assertNotNull(result)
        assertEquals(expedienteModel, result)
        assertTrue(result is Expediente, "El resultado debe ser de tipo Expediente")
    }

    @Test
    @DisplayName("Test que mapea de Expediente Modelo -> Expediente Entity")
    fun expedienteToEntity() {
        val result = expedienteModel.toEntity()
        assertNotNull(result)
        assertEquals(expedienteEntity, result)
        assertTrue(result is ExpedienteEntity, "El resultado debe ser de tipo ExpedienteEntity")
    }

    @Test
    @DisplayName("Test que mapea de Expediente Dto -> Expediente Entity")
    fun expedienteDtoToEntity() {
        val result = expedienteDto.toEntity()
        assertNotNull(result)
        assertEquals(expedienteEntity, result)
        assertTrue(result is ExpedienteEntity, "El resultado debe ser de tipo ExpedienteEntity")
    }
}