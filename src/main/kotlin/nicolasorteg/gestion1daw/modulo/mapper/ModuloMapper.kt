package nicolasorteg.gestion1daw.modulo.mapper

import nicolasorteg.gestion1daw.modulo.dto.ModuloDto
import nicolasorteg.gestion1daw.modulo.model.Modulo

fun Modulo.toDto(): ModuloDto {
    return ModuloDto(
        nombre = nombre,
        siglas = siglas,
        horas = horas
    )
}
fun ModuloDto.toModel(): Modulo {
    return Modulo(
        nombre = nombre,
        siglas = siglas,
        horas = horas
    )
}
