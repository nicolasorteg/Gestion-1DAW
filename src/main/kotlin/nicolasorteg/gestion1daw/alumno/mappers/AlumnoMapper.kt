package nicolasorteg.gestion1daw.alumno.mapper

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
 * Convierte un Alumno (modelo) a AlumnoDto.
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
        notaMedia = this.notaMedia,
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}

/**
 * Convierte un AlumnoEntity a AlumnoDto.
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
        notaMedia = this.notaMedia,
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}

/**
 * Convierte un AlumnoDto a Alumno (modelo).
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
        notaMedia = this.notaMedia ?: 0.0,
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}

/**
 * Convierte un AlumnoEntity a Alumno (modelo).
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
 * Convierte un Alumno (modelo) a AlumnoEntity.
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
 * Convierte un AlumnoDto a AlumnoEntity.
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
        notaMedia = this.notaMedia ?: 0.0,
        faltas = this.faltas,
        retrasos = this.retrasos,
        partes = this.partes
    )
}
