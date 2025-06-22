package modulo.mapper

import nicolasorteg.gestion1daw.modulo.dao.ModuloEntity
import nicolasorteg.gestion1daw.modulo.dto.ModuloDto
import nicolasorteg.gestion1daw.modulo.mapper.toDto
import nicolasorteg.gestion1daw.modulo.mapper.toEntity
import nicolasorteg.gestion1daw.modulo.mapper.toModel
import nicolasorteg.gestion1daw.modulo.model.Modulo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ModuloMapperTest {

    private val moduloModel = Modulo(nombre = "Acceso a Datos", siglas = "AD", horas = 150)
    private val moduloDto = ModuloDto(nombre = "Acceso a Datos", siglas = "AD", horas = 150)
    private val moduloEntity = ModuloEntity(nombre = "Acceso a Datos", siglas = "AD", horas = 150)

    @Test
    @DisplayName("Test que mapea de Modulo Modelo -> Modulo Dto")
    fun moduloToDto() {
        val result = moduloModel.toDto()
        assertNotNull(result)
        assertEquals(moduloDto, result)
        assertTrue(result is ModuloDto, "El resultado debe ser de tipo ModuloDto")
    }

    @Test
    @DisplayName("Test que mapea de Modulo Entity -> Modulo Dto")
    fun moduloEntityToDto() {
        val result = moduloEntity.toDto()
        assertNotNull(result)
        assertEquals(moduloDto, result)
        assertTrue(result is ModuloDto, "El resultado debe ser de tipo ModuloDto")
    }

    @Test
    @DisplayName("Test que mapea de Modulo Dto -> Modulo Modelo")
    fun moduloDtoToModel() {
        val result = moduloDto.toModel()
        assertNotNull(result)
        assertEquals(moduloModel, result)
        assertTrue(result is Modulo, "El resultado debe ser de tipo Modulo")
    }

    @Test
    @DisplayName("Test que mapea de Modulo Entity -> Modulo Modelo")
    fun moduloEntityToModel() {
        val result = moduloEntity.toModel()
        assertNotNull(result)
        assertEquals(moduloModel, result)
        assertTrue(result is Modulo, "El resultado debe ser de tipo Modulo")
    }

    @Test
    @DisplayName("Test que mapea de Modulo Modelo -> Modulo Entity")
    fun moduloToEntity() {
        val result = moduloModel.toEntity()
        assertNotNull(result)
        assertEquals(moduloEntity, result)
        assertTrue(result is ModuloEntity, "El resultado debe ser de tipo ModuloEntity")
    }

    @Test
    @DisplayName("Test que mapea de Modulo Dto -> Modulo Entity")
    fun moduloDtoToEntity() {
        val result = moduloDto.toEntity()
        assertNotNull(result)
        assertEquals(moduloEntity, result)
        assertTrue(result is ModuloEntity, "El resultado debe ser de tipo ModuloEntity")
    }
}