package nicolasorteg.gestion1daw.alumno.repository

import nicolasorteg.gestion1daw.alumno.dao.AlumnoDao
import nicolasorteg.gestion1daw.alumno.mappers.toEntity
import nicolasorteg.gestion1daw.alumno.mappers.toModel
import nicolasorteg.gestion1daw.alumno.models.Alumno
import org.lighthousegames.logging.logging

class AlumnoRepositoryImpl(
    private val dao: AlumnoDao
): AlumnoRepository<Alumno, Int> {
    private val logger = logging()

    override fun save(objeto: Alumno): Alumno {
        val alumnoEntity = objeto.toEntity()
        val generatedId = dao.save(alumnoEntity)
        val savedEntity = dao.findById(generatedId) ?: throw IllegalStateException("Error al guardar el alumno.")
        return savedEntity.toModel()
    }

    override fun getAll(): List<Alumno> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Alumno? {
        TODO("Not yet implemented")
    }

    override fun update(objeto: Alumno, id: Int): Alumno? {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): Alumno? {
        TODO("Not yet implemented")
    }
}