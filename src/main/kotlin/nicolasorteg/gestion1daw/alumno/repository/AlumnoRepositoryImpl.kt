package nicolasorteg.gestion1daw.alumno.repository

import nicolasorteg.gestion1daw.alumno.dao.AlumnoDao
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.mappers.toEntity
import nicolasorteg.gestion1daw.alumno.mappers.toModel
import org.lighthousegames.logging.logging

/**
 * Implementación del repositorio.
 */
class AlumnoRepositoryImpl(
    private val dao: AlumnoDao
): AlumnoRepository<Alumno, Int> {

    private val logger = logging()

    /**
     * Guarda a un alumno en la BD.
     *
     * @param objeto Alumno a guardar.
     * @return Alumno guardado con el ID generado.
     * @throws IllegalStateException si la operación de guardado falla.
     */
    override fun save(objeto: Alumno): Alumno {
        val alumnoEntity = objeto.toEntity()
        val generatedId = dao.save(alumnoEntity)
        val savedEntity = dao.findById(generatedId) ?: throw IllegalStateException("Error al guardar el alumno.")
        return savedEntity.toModel()
    }

    /**
     * Recupera todos los alumnos almacenados.
     *
     * @return Lista con todos los alumnos.
     */
    override fun getAll(): List<Alumno> {
        logger.debug { "Recuperando todos los alumnos" }
        return dao.findAll().map { it.toModel() }
    }

    /**
     * Obtiene un alumno por su ID.
     *
     * @param id Identificador del alumno.
     * @return Alumno correspondiente o null si no existe.
     */
    override fun getById(id: Int): Alumno? {
        logger.debug { "Buscando alumno con id = $id" }
        return dao.findById(id)?.toModel()
    }

    /**
     * Actualiza un alumno existente.
     *
     * @param objeto Datos actualizados del alumno.
     * @param id Identificador del alumno a actualizar.
     * @return Alumno actualizado o null si no existe.
     */
    override fun update(objeto: Alumno, id: Int): Alumno? {
        logger.debug { "Actualizando alumno con id = $id" }

        val alumnoExistente = dao.findById(id) ?: return null

        val alumnoActualizado = Alumno(
            id = id,
            nombre = objeto.nombre,
            apellidos = objeto.apellidos,
            fechaNacimiento = objeto.fechaNacimiento,
            edad = objeto.edad,
            nacionalidad = objeto.nacionalidad,
            fechaIncorporacion = objeto.fechaIncorporacion,
            modulos = objeto.modulos,
            expediente = objeto.expediente,
            notaMedia = objeto.notaMedia,
            faltas = objeto.faltas,
            retrasos = objeto.retrasos,
            partes = objeto.partes
        ).toEntity()

        val updatedRows = dao.update(alumnoActualizado)
        return if (updatedRows > 0) dao.findById(id)?.toModel() else null
    }


    /**
     * Elimina un alumno en base a su ID.
     *
     * @param id Identificador del alumno a eliminar.
     * @return Alumno eliminado o null si no se encontró.
     */
    override fun delete(id: Int): Alumno? {
        logger.debug { "Eliminando alumno con id = $id" }
        val alumno = dao.findById(id)?.toModel()
        if (alumno != null) {
            dao.delete(id)
        }
        return alumno
    }
}
