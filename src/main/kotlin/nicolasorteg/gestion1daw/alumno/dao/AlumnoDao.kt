package nicolasorteg.gestion1daw.alumno.dao

import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

/**
 * Para gestionar el CRUD sobre alumnos
 *
 * @see AlumnoEntity para la representación de cada fila.
 */
@RegisterKotlinMapper(AlumnoEntity::class)
interface AlumnoDao {

    // obtenerlos todos
    @SqlQuery("SELECT id, nombre, apellidos, fecha_nacimiento AS fechaNacimiento, edad, nacionalidad, fecha_incorporacion AS fechaIncorporacion, nota_media AS notaMedia, faltas, retrasos, partes FROM alumnos")
    fun findAll(): List<AlumnoEntity>

    // obtener en base a ID
    @SqlQuery("SELECT id, nombre, apellidos, fecha_nacimiento AS fechaNacimiento, edad, nacionalidad, fecha_incorporacion AS fechaIncorporacion, nota_media AS notaMedia, faltas, retrasos, partes FROM alumnos WHERE id = :id")
    fun findById(@Bind("id") id: Int): AlumnoEntity?

    // crear
    @SqlUpdate("INSERT INTO alumnos (nombre, apellidos, fecha_nacimiento, edad, nacionalidad, fecha_incorporacion, nota_media, faltas, retrasos, partes) VALUES (:nombre, :apellidos, :fechaNacimiento, :edad, :nacionalidad, :fechaIncorporacion, :notaMedia, :faltas, :retrasos, :partes)")
    @GetGeneratedKeys("id")
    fun save(@BindBean alumno: AlumnoEntity): Int

    // actualizar datos
    @SqlUpdate("UPDATE alumnos SET nombre = :nombre, apellidos = :apellidos, fecha_nacimiento = :fechaNacimiento, edad = :edad, nacionalidad = :nacionalidad, fecha_incorporacion = :fechaIncorporacion, nota_media = :notaMedia, faltas = :faltas, retrasos = :retrasos, partes = :partes WHERE id = :id")
    fun update(@BindBean alumno: AlumnoEntity): Int

    // borrar en base a ID
    @SqlUpdate("DELETE FROM alumnos WHERE id = :id")
    fun delete(@Bind("id") id: Int): Int

    // borrar todos
    @SqlUpdate("DELETE FROM alumnos")
    fun deleteAll(): Int
}
