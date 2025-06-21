package nicolasorteg.gestion1daw.alumno.dao

import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

@RegisterKotlinMapper(AlumnoEntity::class)
interface AlumnoDao {

    @SqlQuery("SELECT id, nombre, apellidos, fecha_nacimiento AS fechaNacimiento, edad, nacionalidad, fecha_incorporacion AS fechaIncorporacion, nota_media AS notaMedia, faltas, retrasos, partes FROM alumnos")
    fun findAll(): List<AlumnoEntity>

    @SqlQuery("SELECT id, nombre, apellidos, fecha_nacimiento AS fechaNacimiento, edad, nacionalidad, fecha_incorporacion AS fechaIncorporacion, nota_media AS notaMedia, faltas, retrasos, partes FROM alumnos WHERE id = :id")
    fun findById(@Bind("id") id: Int): AlumnoEntity?

    @SqlUpdate("INSERT INTO alumnos (nombre, apellidos, fecha_nacimiento, edad, nacionalidad, fecha_incorporacion, nota_media, faltas, retrasos, partes) VALUES (:nombre, :apellidos, :fechaNacimiento, :edad, :nacionalidad, :fechaIncorporacion, :notaMedia, :faltas, :retrasos, :partes)")
    @GetGeneratedKeys("id")
    fun save(@BindBean alumno: AlumnoEntity): Int

    @SqlUpdate("UPDATE alumnos SET nombre = :nombre, apellidos = :apellidos, fecha_nacimiento = :fechaNacimiento, edad = :edad, nacionalidad = :nacionalidad, fecha_incorporacion = :fechaIncorporacion, nota_media = :notaMedia, faltas = :faltas, retrasos = :retrasos, partes = :partes WHERE id = :id")
    fun update(@BindBean alumno: AlumnoEntity): Int

    @SqlUpdate("DELETE FROM alumnos WHERE id = :id")
    fun delete(@Bind("id") id: Int): Int

    @SqlUpdate("DELETE FROM alumnos")
    fun deleteAll(): Int
}
