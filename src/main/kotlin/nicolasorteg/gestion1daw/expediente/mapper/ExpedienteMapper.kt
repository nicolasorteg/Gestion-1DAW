package nicolasorteg.gestion1daw.expediente.mapper

import nicolasorteg.gestion1daw.expediente.dao.ExpedienteEntity
import nicolasorteg.gestion1daw.expediente.dto.ExpedienteDto
import nicolasorteg.gestion1daw.expediente.model.Expediente

/**
 * Convierte un Expediente a su DTO.
 */
fun Expediente.toDto(): ExpedienteDto {
    return ExpedienteDto(
        calificaciones = this.calificaciones,
        notaMedia = this.notaMedia,
        observaciones = this.observaciones
    )
}

/**
 * Convierte un ExpedienteEntity a su DTO.
 */
fun ExpedienteEntity.toDto(): ExpedienteDto {
    return ExpedienteDto(
        calificaciones = this.calificaciones,
        notaMedia = this.notaMedia,
        observaciones = this.observaciones
    )
}

/**
 * Convierte un ExpedienteDto a su Model.
 */
fun ExpedienteDto.toModel(): Expediente {
    return Expediente(
        calificaciones = this.calificaciones,
        notaMedia = this.notaMedia,
        observaciones = this.observaciones
    )
}

/**
 * Convierte un ExpedienteEntity a su Model.
 */
fun ExpedienteEntity.toModel(): Expediente {
    return Expediente(
        calificaciones = this.calificaciones,
        notaMedia = this.notaMedia,
        observaciones = this.observaciones
    )
}

/**
 * Convierte un Expediente a su Entity.
 */
fun Expediente.toEntity(): ExpedienteEntity {
    return ExpedienteEntity(
        calificaciones = this.calificaciones,
        notaMedia = this.notaMedia,
        observaciones = this.observaciones
    )
}

/**
 * Convierte un ExpedienteDto a su Entity.
 */
fun ExpedienteDto.toEntity(): ExpedienteEntity {
    return ExpedienteEntity(
        calificaciones = this.calificaciones,
        notaMedia = this.notaMedia,
        observaciones = this.observaciones
    )
}