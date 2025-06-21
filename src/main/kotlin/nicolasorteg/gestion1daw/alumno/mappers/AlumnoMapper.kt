package nicolasorteg.gestion1daw.alumno.mappers

import nicolasorteg.gestion1daw.alumno.dao.AlumnoEntity
import nicolasorteg.gestion1daw.alumno.dto.AlumnoDto
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.expediente.mapper.toDto
import nicolasorteg.gestion1daw.expediente.mapper.toEntity
import nicolasorteg.gestion1daw.expediente.mapper.toModel
import nicolasorteg.gestion1daw.modulo.mapper.toDto
import nicolasorteg.gestion1daw.modulo.mapper.toEntity
import nicolasorteg.gestion1daw.modulo.mapper.toModel

/**
 * Convierte un Alumno a su DTO.
 */
fun Alumno.toDto(): AlumnoDto {
    return AlumnoDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        edad = this.edad,
        nacionalidad = this.nacionalidad,
        fechaIncorporacion = this.fechaIncorporacion,
        modulos = this.modulos.map { it.toDto() },
        expediente = this.expediente.toDto(),
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}

/**
 * Convierte un AlumnoEntity a su DTO.
 */
fun AlumnoEntity.toDto(): AlumnoDto {
    return AlumnoDto(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        edad = this.edad,
        nacionalidad = this.nacionalidad,
        fechaIncorporacion = this.fechaIncorporacion,
        modulos = this.modulos.map { it.toDto() },
        expediente = this.expediente.toDto(),
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}

/**
 * Convierte un AlumnoDto a su Model.
 */
fun AlumnoDto.toModel(): Alumno {
    return Alumno(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        edad = this.edad,
        nacionalidad = this.nacionalidad,
        fechaIncorporacion = this.fechaIncorporacion,
        modulos = this.modulos.map { it.toModel() },
        expediente = this.expediente.toModel(),
        notaMedia = this.expediente.notaMedia,
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}

/**
 * Convierte un AlumnoEntity a su Model.
 */
fun AlumnoEntity.toModel(): Alumno {
    return Alumno(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        edad = this.edad,
        nacionalidad = this.nacionalidad,
        fechaIncorporacion = this.fechaIncorporacion,
        modulos = this.modulos.map { it.toModel() },
        expediente = this.expediente.toModel(),
        notaMedia = this.notaMedia,
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}

/**
 * Convierte un Alumno a su Entity.
 */
fun Alumno.toEntity(): AlumnoEntity {
    return AlumnoEntity(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        edad = this.edad,
        nacionalidad = this.nacionalidad,
        fechaIncorporacion = this.fechaIncorporacion,
        modulos = this.modulos.map { it.toEntity() },
        expediente = this.expediente.toEntity(),
        notaMedia = this.notaMedia,
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}

/**
 * Convierte un AlumnoDto a su Entity.
 */
fun AlumnoDto.toEntity(): AlumnoEntity {
    return AlumnoEntity(
        id = this.id,
        nombre = this.nombre,
        apellidos = this.apellidos,
        fechaNacimiento = this.fechaNacimiento,
        edad = this.edad,
        nacionalidad = this.nacionalidad,
        fechaIncorporacion = this.fechaIncorporacion,
        modulos = this.modulos.map { it.toEntity() },
        expediente = this.expediente.toEntity(),
        notaMedia = this.expediente.notaMedia,
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}