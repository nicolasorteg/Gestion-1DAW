package nicolasorteg.gestion1daw.alumno.repository

/**
 * Aquí se almacenan las operaciones CRUD
 *
 */
interface AlumnoRepository<T, ID> {
    fun save(objeto: T):T               // C
    fun getAll(): List<T>               // R
    fun getById(id: ID): T?             // R
    fun update(objeto:T,id: ID):T?      // U
    fun delete(id: ID):T?               // D
}