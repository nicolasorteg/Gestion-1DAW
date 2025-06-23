
package nicolasorteg.gestion1daw.common.database

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.statement.SqlLogger
import org.jdbi.v3.core.statement.StatementContext

import org.lighthousegames.logging.logging
import java.io.File
import java.time.temporal.ChronoUnit


class JdbiManager (
    private val databaseUrl : String,
    private val databaseInitData : Boolean,
    private val databaseInitTables : Boolean,
    private val databaseLogger : Boolean
){
    private val logger = logging()

    // para que Jdbi se cree cuando se necesite
    val jdbi by lazy {
        Jdbi.create(this.databaseUrl)
    }

    init {

        jdbi.installPlugins()

        if (databaseLogger) {
            val sqlLogger: SqlLogger = object : SqlLogger {
                override fun logAfterExecution(context: StatementContext) {
                    logger.debug {
                        "Query executed: " + "sql ${context.renderedSql}, parameters ${context.binding}, timeTaken ${
                            context.getElapsedTime(
                                ChronoUnit.MILLIS
                            )
                        } ms"
                    }
                }
            }
            jdbi.setSqlLogger(sqlLogger)
        }


        // ejecucion de scipts
        if (databaseInitTables) {
            logger.debug { "Obteniendo tablas del programa" }
            executeSqlScriptFromResources("tables.sql")
        }


        if (databaseInitData) {
            logger.debug { "Database initialized" }
            executeSqlScriptFromResources("data.sql")
        }
    }

    fun executeSqlScriptFromResources(resourcePath: String) {
        logger.debug { "Ejecutando script SQL desde recursos: $resourcePath" }
        val inputStream = ClassLoader.getSystemResourceAsStream(resourcePath)?.bufferedReader()!!
        val script = inputStream.readText()
        jdbi.useHandle<Exception> { handle ->
            handle.createScript(script).execute()
        }
    }

    fun executeSqlScript(scriptFilePath: String) {
        logger.debug { "Ejecutando script SQL: $scriptFilePath" }
        val script = File(scriptFilePath).readText()
        jdbi.useHandle<Exception> { handle ->
            handle.createScript(script).execute()
        }
    }
}
