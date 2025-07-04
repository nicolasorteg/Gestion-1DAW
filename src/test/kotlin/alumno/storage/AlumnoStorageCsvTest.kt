    package alumno.storage

    import com.github.michaelbull.result.get
    import com.github.michaelbull.result.getError
    import nicolasorteg.gestion1daw.alumno.error.AlumnoError
    import nicolasorteg.gestion1daw.alumno.models.Alumno
    import nicolasorteg.gestion1daw.alumno.storage.AlumnoStorageCsv
    import nicolasorteg.gestion1daw.expediente.model.Expediente
    import nicolasorteg.gestion1daw.modulo.model.Modulo
    import org.junit.jupiter.api.*
    import java.io.File

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class AlumnoStorageCsvTest {

        private val tempFile = File("data_copia/test_alumnos.csv")
        private val expedienteFile = File("data/expedientes.csv")
        private val matriculasFile = File("data/matriculas.csv")
        private val storage = AlumnoStorageCsv()

        @BeforeAll
        fun setup() {
            expedienteFile.parentFile.mkdirs()
            expedienteFile.writeText(
                """"
                idAlumno,notaMedia,observaciones,PROG,8.5,BBDD,9.0
                1,8.75,Observaciones del alumno,PROG,8.5,BBDD,9.0
                """.trimIndent()
            )

            matriculasFile.writeText(
                """
                idAlumno,siglaModulo
                1,PROG
                1,BBDD
                """.trimIndent()
            )
        }

        @Test
        @DisplayName("Test de escritura y lectura de alumnos correctamente")
        fun testLeeYEscribreCsv() {
            val alumno = Alumno(
                id = 1,
                nombre = "Juan",
                apellidos = "Pérez",
                fechaNacimiento = "2000-01-01",
                edad = 24,
                nacionalidad = "España",
                fechaIncorporacion = "2020-09-01",
                modulos = listOf(
                    Modulo("Programación", "PROG", 120),
                    Modulo("Bases de Datos", "BBDD", 80)
                ),
                expediente = Expediente(
                    calificaciones = mapOf("PROG" to 8.5, "BBDD" to 9.0),
                    notaMedia = 8.75,
                    observaciones = "Observaciones del alumno"
                ),
                notaMedia = 8.75,
                faltas = 3,
                retrasos = 1,
                partes = 0
            )

            // Escritura
            val writeResult = storage.writeToFile(tempFile, listOf(alumno))
            Assertions.assertTrue(writeResult.isOk)
            Assertions.assertTrue(tempFile.exists())

            // Lectura
            val readResult = storage.readFromFile(tempFile)
            Assertions.assertTrue(readResult.isOk)

            val alumnos = readResult.get()!!
            Assertions.assertEquals(1, alumnos.size)

            val readAlumno = alumnos[0]
            Assertions.assertEquals(alumno.nombre, readAlumno.nombre)
            Assertions.assertEquals(alumno.modulos.map { it.siglas }, readAlumno.modulos.map { it.siglas })
            Assertions.assertEquals(alumno.expediente.notaMedia, readAlumno.expediente.notaMedia)
        }

        @Test
        @DisplayName("Test error al leer archivo inexistente")
        fun testLeerArchivoInexistente() {
            val fakeFile = File("no_existe.csv")
            val result = storage.readFromFile(fakeFile)
            Assertions.assertTrue(result.getError() is AlumnoError.AlumnoStorageError)
        }

        @Test
        @DisplayName("Test error al escribir archivo con directorio inválido")
        fun testEscribirAUnArchivoInvalido() {
            val invalidFile = File("Z:/no_such_directory/invalid.csv")
            val alumno = Alumno(
                id = 1,
                nombre = "Test",
                apellidos = "Alumno",
                fechaNacimiento = "2000-01-01",
                edad = 23,
                nacionalidad = "España",
                fechaIncorporacion = "2022-09-01",
                modulos = emptyList(),
                expediente = Expediente(emptyMap(), 0.0, ""),
                notaMedia = 0.0,
                faltas = 0,
                retrasos = 0,
                partes = 0
            )

            val result = storage.writeToFile(invalidFile, listOf(alumno))
            Assertions.assertTrue(result.isErr)
            Assertions.assertTrue(result.getError() is AlumnoError.AlumnoStorageError)
        }

        @Test
        fun testLeerCsvMalFormado() {
            tempFile.writeText(
                """
            id,nombre,apellidos,fechaNacimiento,edad,nacionalidad,fechaIncorporacion,notaMedia,faltas,retrasos,partes
            1,Juan,Pérez,2000-01-01,twenty-four,España,2020-09-01,8.75,3,1,0
            """.trimIndent()
            )

            val result = storage.readFromFile(tempFile)
            Assertions.assertTrue(result.isErr)
            Assertions.assertTrue(result.getError() is AlumnoError.AlumnoStorageError)
        }

        @Test
        fun testLeerSinExpedientes() {
            expedienteFile.renameTo(File("data/expedientes_backup.csv"))
            try {
                val result = storage.readFromFile(tempFile)
                Assertions.assertTrue(result.isErr)
                Assertions.assertTrue(result.getError() is AlumnoError.AlumnoStorageError)
            } finally {
                File("data/expedientes_backup.csv").renameTo(expedienteFile)
            }
        }

        @Test
        fun testEscribirListaVacia() {
            val result = storage.writeToFile(tempFile, emptyList())
            Assertions.assertTrue(result.isOk)
            Assertions.assertTrue(tempFile.exists())
        }

        @Test
        fun testEscribirEnArchivoCreaParentDirs() {
            val newDir = File("data_copia/nuevo_directorio")
            val fileInNewDir = File(newDir, "alumnos.csv")

            // condicional simple para asegurarnos que no exista
            if (newDir.exists()) newDir.deleteRecursively()

            val alumno = Alumno(
                id = 2,
                nombre = "Pedro",
                apellidos = "Gómez",
                fechaNacimiento = "1999-05-05",
                edad = 26,
                nacionalidad = "España",
                fechaIncorporacion = "2021-09-01",
                modulos = emptyList(),
                expediente = Expediente(emptyMap(), 0.0, ""),
                notaMedia = 0.0,
                faltas = 0,
                retrasos = 0,
                partes = 0
            )
        }

        @Test
        fun testLeerAlumnoSinExpedienteOMatricula() {
            tempFile.writeText(
                """
            id,nombre,apellidos,fechaNacimiento,edad,nacionalidad,fechaIncorporacion,notaMedia,faltas,retrasos,partes
            999,Pepe,Lopez,1990-01-01,30,España,2019-09-01,5.0,0,0,0
            """.trimIndent()
            )


            expedienteFile.writeText(
                """
            idAlumno,notaMedia,observaciones
            1,8.75,Observaciones del alumno
            """.trimIndent()
            )
            matriculasFile.writeText(
                """
            idAlumno,siglaModulo
            1,PROG
            1,BBDD
            """.trimIndent()
            )

            val result = storage.readFromFile(tempFile)
            Assertions.assertTrue(result.isOk)
            val alumnos = result.get()!!
            Assertions.assertEquals(1, alumnos.size)

            val alumno = alumnos[0]
            Assertions.assertEquals(999, alumno.id)
            Assertions.assertTrue(alumno.expediente.calificaciones.isEmpty())
            Assertions.assertEquals("", alumno.expediente.observaciones)
            Assertions.assertTrue(alumno.modulos.isEmpty())
        }

        @Test
        fun testExpedienteMalFormadoIgnorado() {
            expedienteFile.writeText(
                """
            idAlumno,notaMedia,observaciones,PROG
            1,8.75
            2,not_a_number,Observaciones
            """.trimIndent()
            )
            tempFile.writeText(
                """
            id,nombre,apellidos,fechaNacimiento,edad,nacionalidad,fechaIncorporacion,notaMedia,faltas,retrasos,partes
            1,Ana,Gomez,1999-05-01,25,España,2021-09-01,8.75,0,0,0
            2,Luis,Ramos,1998-03-15,26,España,2020-09-01,7.0,1,0,0
            """.trimIndent()
            )
            val result = storage.readFromFile(tempFile)
            Assertions.assertTrue(result.isOk)
            Assertions.assertEquals(2, result.get()!!.size)
            Assertions.assertTrue(result.get()!![0].expediente.calificaciones.isEmpty())
        }

        @Test
        fun testMatriculaMalFormadaIgnorada() {
            matriculasFile.writeText(
                """
            idAlumno,siglaModulo
            ,PROG
            1,
            abc,BBDD
            1,PROG
            """.trimIndent()
            )
            expedienteFile.writeText(
                """
            idAlumno,notaMedia,observaciones
            1,8.0,Correcto
            """.trimIndent()
            )
            tempFile.writeText(
                """
            id,nombre,apellidos,fechaNacimiento,edad,nacionalidad,fechaIncorporacion,notaMedia,faltas,retrasos,partes
            1,Maria,Lopez,2000-04-01,24,España,2020-09-01,8.0,0,0,0
            """.trimIndent()
            )
            val result = storage.readFromFile(tempFile)
            Assertions.assertTrue(result.isOk)
            val alumno = result.get()!![0]
            Assertions.assertEquals(1, alumno.modulos.size)
            Assertions.assertEquals("PROG", alumno.modulos[0].siglas)
        }

        @Test
        fun testEscrituraConArchivoDeSoloLectura() {

            // creacion archivo
            tempFile.writeText("")

            // solo lectura
            tempFile.setReadOnly()

            val alumno = Alumno(
                id = 3, nombre = "Carlos", apellidos = "Vega", fechaNacimiento = "1995-01-01", edad = 29,
                nacionalidad = "España", fechaIncorporacion = "2015-09-01", modulos = emptyList(),
                expediente = Expediente(emptyMap(), 0.0, ""), notaMedia = 0.0, faltas = 0, retrasos = 0, partes = 0
            )

            val result = storage.writeToFile(tempFile, listOf(alumno))
            Assertions.assertTrue(result.isErr)
            Assertions.assertTrue(result.getError() is AlumnoError.AlumnoStorageError)

            tempFile.setWritable(true)
        }


        @Test
        @DisplayName("Test módulos no definidos en expediente o matrícula")
        fun testModuloNoDefinido() {
            tempFile.writeText(
                """
        id,nombre,apellidos,fechaNacimiento,edad,nacionalidad,fechaIncorporacion,notaMedia,faltas,retrasos,partes
        1,Juan,Pérez,2000-01-01,24,España,2020-09-01,8.75,3,1,0
        """.trimIndent()
            )
            expedienteFile.writeText(
                """
        idAlumno,notaMedia,observaciones,NOEXISTE
        1,8.75,Observaciones,7.5
        """.trimIndent()
            )
            matriculasFile.writeText(
                """
        idAlumno,siglaModulo
        1,NOEXISTE
        """.trimIndent()
            )

            val result = storage.readFromFile(tempFile)
            Assertions.assertTrue(result.isOk)
            val alumno = result.get()!![0]

           Assertions.assertTrue(alumno.modulos.none { it.siglas == "NOEXISTE" })
        }

        @Test
        @DisplayName("Test expediente con IDs duplicados")
        fun testExpedienteConIdsDuplicados() {
            tempFile.writeText(
                """
        id,nombre,apellidos,fechaNacimiento,edad,nacionalidad,fechaIncorporacion,notaMedia,faltas,retrasos,partes
        1,Juan,Pérez,2000-01-01,24,España,2020-09-01,8.75,3,1,0
        """.trimIndent()
            )
            expedienteFile.writeText(
                """
        idAlumno,notaMedia,observaciones,PROG
        1,8.75,Observaciones,8.5
        1,9.00,Observaciones duplicadas,9.0
        """.trimIndent()
            )
            matriculasFile.writeText(
                """
        idAlumno,siglaModulo
        1,PROG
        """.trimIndent()
            )

            val result = storage.readFromFile(tempFile)

            Assertions.assertTrue(result.isOk)

            val alumno = result.get()!![0]
            Assertions.assertEquals(1, alumno.id)
            }



        @AfterAll
        fun cleanup() {
            tempFile.delete()
            expedienteFile.delete()
            matriculasFile.delete()
        }
    }
