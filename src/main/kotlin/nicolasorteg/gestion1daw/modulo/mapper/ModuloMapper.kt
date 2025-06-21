package nicolasorteg.gestion1daw.modulo.mapper

import nicolasorteg.gestion1daw.modulo.dao.ModuloEntity
import nicolasorteg.gestion1daw.modulo.dto.ModuloDto
import nicolasorteg.gestion1daw.modulo.model.Modulo

/**
 * Convierte un Modulo a su DTO.
 */
fun Modulo.toDto(): ModuloDto {
    return ModuloDto(
        nombre = this.nombre,
        siglas = this.siglas,
        horas = this.horas
    )
}

/**
 * Convierte un ModuloEntity a su DTO.
 */
fun ModuloEntity.toDto(): ModuloDto {
    return ModuloDto(
        nombre = this.nombre,
        siglas = this.siglas,
        horas = this.horas
    )
}

/**
 * Convierte un ModuloDto a su Model.
 */
fun ModuloDto.toModel(): Modulo {
    return Modulo(
        nombre = this.nombre,
        siglas = this.siglas,
        horas = this.horas
    )
}

/**
 * Convierte un ModuloEntity a su Model.
 */
fun ModuloEntity.toModel(): Modulo {
    return Modulo(
        nombre = this.nombre,
        siglas = this.siglas,
        horas = this.horas
    )
}

/**
 * Convierte un Modulo a su Entity.
 */
fun Modulo.toEntity(): ModuloEntity {
    return ModuloEntity(
        nombre = this.nombre,
        siglas = this.siglas,
        horas = this.horas
    )
}

/**
 * Convierte un ModuloDto a su Entity.
 */
fun ModuloDto.toEntity(): ModuloEntity {
    return ModuloEntity(
        nombre = this.nombre,
        siglas = this.siglas,
        horas = this.horas
    )
}