package nicolasorteg.gestion1daw.alumno.repository

import nicolasorteg.gestion1daw.alumno.dao.AlumnoDao
import nicolasorteg.gestion1daw.alumno.models.Alumno
import org.lighthousegames.logging.logging

class AlumnoRepositoryImpl(
    private val dao: AlumnoDao
): AlumnoRepository<Alumno, Int> {
    private val logger = logging()

    override fun save(objeto: Alumno): Alumno {
        TODO("Not yet implemented")
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