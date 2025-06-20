package nicolasorteg.gestion1daw.expediente.mapper

import nicolasorteg.gestion1daw.expediente.dto.ExpedienteDto
import nicolasorteg.gestion1daw.expediente.model.Expediente

fun Expediente.toDto(): ExpedienteDto {
    return ExpedienteDto(
        calificaciones = calificaciones,
        notaMedia = notaMedia,
        observaciones = observaciones
    )
}
fun ExpedienteDto.toModel(): Expediente {
    return Expediente(
        calificaciones = calificaciones,
        notaMedia = notaMedia,
        observaciones = observaciones
    )
}
