package nicolasorteg.gestion1daw.alumno.service

import com.github.michaelbull.result.Result
/**
 * Interfaz que represeta las operaciones CRUD versión servicio
 */
interface AlumnoService<T,E,ID> {
    fun getAll():Result<List<T>,E>
    fun getByID(id:ID): Result<T, E>
    fun save(item: T): Result<T,E>
    fun delete(id: ID): Result<T,E>
    fun update(id: ID, item: T): Result<T,E>
}