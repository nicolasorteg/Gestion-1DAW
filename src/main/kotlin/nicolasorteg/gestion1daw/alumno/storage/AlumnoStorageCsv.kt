package nicolasorteg.gestion1daw.alumno.storage

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Err
import nicolasorteg.gestion1daw.alumno.error.PersonaError
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.modulo.model.Modulo
import org.lighthousegames.logging.logging
import java.io.File

class AlumnoStorageCsv : AlumnoStorage {
    private val logger = logging()

    override fun readFromFile(file: File): Result<List<Alumno>, PersonaError> {
        logger.debug { "Leyendo archivo CSV de alumnos: ${file.path}" }

        // llamamos a los archivos .csv
        val expedienteFile = File("data/expedientes.csv")
        val matriculasFile = File("data/matriculas.csv")
        val modulos = getModulos()

        val requiredFiles = listOf(file, expedienteFile, matriculasFile)
        requiredFiles.forEach {

            // condicional para verificar que se existen y se pueden leer
            if (!it.exists() || !it.canRead()) {
                logger.error { "El fichero no existe o no se puede leer: ${it.path}" }
                return Err(PersonaError.PersonaStorageError("El fichero no existe o no se puede leer: ${it.path}"))
            }
        }

        return try {
            // lectura de los expedientes
            val expedientesMap = expedienteFile.readLines()
                .drop(1)
                .mapNotNull { line ->
                    val campos = line.split(",").map { it.trim() }
                    try {
                        if (campos.size < 3) {
                            logger.warn { "Línea de expediente inválida: $line" }
                            return@mapNotNull null
                        }

                        val idAlumno = campos[0].toInt()
                        val notaMedia = campos[1].toDouble()
                        val observaciones = campos[2]
                        val calificaciones = campos.drop(3)
                            .chunked(2)
                            .mapNotNull {
                                if (it.size < 2) null
                                else it[0] to it[1].toDoubleOrNull()
                            }
                            .filter { it.second != null }
                            .associate { it.first to it.second!! }

                        idAlumno to Expediente(calificaciones, notaMedia, observaciones)
                    } catch (e: Exception) {
                        logger.warn { "Error procesando expediente: $line -> ${e.message}" }
                        null
                    }
                }.toMap()

            // lectura de las matrículas
            val modulosMap = modulos.associateBy { it.siglas }
            val matriculasMap = matriculasFile.readLines()
                .drop(1)
                .mapNotNull { line ->
                    val campos = line.split(",")
                    if (campos.size < 2) {
                        logger.warn { "Línea de matrícula inválida: $line" }
                        null
                    } else {
                        try {
                            campos[0].toInt() to campos[1].trim()
                        } catch (e: Exception) {
                            logger.warn { "Error en matrícula: $line -> ${e.message}" }
                            null
                        }
                    }
                }
                .groupBy({ it.first }, { it.second })

            // lectura de los alumnos
            val alumnos = file.readLines().drop(1).map { line ->
                val campos = line.split(",").map { it.trim() }

                val id = campos[0].toInt()
                val nombre = campos[1]
                val apellidos = campos[2]
                val fechaNacimiento = campos[3]
                val edad = campos[4].toInt()
                val nacionalidad = campos[5]
                val fechaIncorporacion = campos[6]
                val notaMedia = campos[7].toDouble()
                val faltas = campos[8].toInt()
                val retrasos = campos[9].toInt()
                val partes = campos[10].toInt()

                val expediente = expedientesMap[id] ?: Expediente(emptyMap(), notaMedia, "")
                val modulosAlumno = matriculasMap[id]?.mapNotNull { sigla -> modulosMap[sigla] } ?: emptyList()

                Alumno(
                    id = id,
                    nombre = nombre,
                    apellidos = apellidos,
                    fechaNacimiento = fechaNacimiento,
                    edad = edad,
                    nacionalidad = nacionalidad,
                    fechaIncorporacion = fechaIncorporacion,
                    modulos = modulosAlumno,
                    expediente = expediente,
                    notaMedia = notaMedia,
                    faltas = faltas,
                    retrasos = retrasos,
                    partes = partes
                )
            }

            logger.info { "✅ Alumnos leídos correctamente: ${alumnos.size}" }
            Ok(alumnos)
        } catch (e: Exception) {
            logger.error { "⚠️ Error leyendo archivo CSV: ${e.message}" }
            Err(PersonaError.PersonaStorageError("Error leyendo archivo CSV: ${e.message}"))
        }
    }

    override fun writeToFile(file: File, alumno: List<Alumno>): Result<String, PersonaError> {
        logger.debug { "Escribiendo alumnos en archivo: ${file.path}" }

        try {
            val parent = file.parentFile
            if (parent != null && !parent.exists()) parent.mkdirs()

            // escritura alumnos
            file.writeText("id,nombre,apellidos,fechaNacimiento,edad,nacionalidad,fechaIncorporacion,notaMedia,faltas,retrasos,partes\n")

            alumno.forEach {
                val line = listOf(
                    it.id, it.nombre, it.apellidos, it.fechaNacimiento, it.edad,
                    it.nacionalidad, it.fechaIncorporacion, it.notaMedia,
                    it.faltas, it.retrasos, it.partes
                ).joinToString(",")

                file.appendText("$line\n")
            }

            logger.info { "✅ Alumnos escritos correctamente en ${file.path}" }
            return Ok(file.absolutePath)
        } catch (e: Exception) {
            logger.error { "Error al escribir archivo: ${e.message}" }
            return Err(PersonaError.PersonaStorageError("Error al escribir archivo: ${e.message}"))
        }
    }

    private fun getModulos(): List<Modulo> = listOf(
        Modulo("Programación", "PROG", 120),
        Modulo("Bases de Datos", "BBDD", 80),
        Modulo("Sistemas Informáticos", "SI", 80),
        Modulo("Lenguaje de Marcas", "LMM", 60),
        Modulo("Itinerario Para la Empleabilidad", "IPE", 60),
        Modulo("Entornos de Desarrollo", "EED", 40),
        Modulo("Optimización y posicionamiento SEO", "SEO", 40)
    )
}
