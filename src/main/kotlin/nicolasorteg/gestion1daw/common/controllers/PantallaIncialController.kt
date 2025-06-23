package nicolasorteg.gestion1daw.common.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.stage.FileChooser
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.routes.RoutesManager
import nicolasorteg.gestion1daw.common.viewmodels.PantallaInicialViewModel
import nicolasorteg.gestion1daw.expediente.model.Expediente
import org.lighthousegames.logging.logging
import java.io.File

class PantallaInicialController {

    private val logger = logging()
    private val viewModel = PantallaInicialViewModel()

    @FXML private lateinit var tablaAlumnos: TableView<Alumno>
    @FXML private lateinit var colId: TableColumn<Alumno, Number>
    @FXML private lateinit var colNombre: TableColumn<Alumno, String>
    @FXML private lateinit var colApellidos: TableColumn<Alumno, String>
    @FXML private lateinit var colEdad: TableColumn<Alumno, Number>
    @FXML private lateinit var colNotaMedia: TableColumn<Alumno, Number>
    @FXML private lateinit var colFechaNacimiento: TableColumn<Alumno, String>
    @FXML private lateinit var colNacionalidad: TableColumn<Alumno, String>
    @FXML private lateinit var colFechaIncorporacion: TableColumn<Alumno, String>
    @FXML private lateinit var colFaltas: TableColumn<Alumno, Number>
    @FXML private lateinit var colRetrasos: TableColumn<Alumno, Number>
    @FXML private lateinit var colPartes: TableColumn<Alumno, Number>
    @FXML private lateinit var inputBuscar: TextField
    @FXML private lateinit var inputNombre: TextField
    @FXML private lateinit var inputApellidos: TextField
    @FXML private lateinit var inputEdad: TextField
    @FXML private lateinit var inputFechaNacimiento: TextField
    @FXML private lateinit var inputNacionalidad: TextField
    @FXML private lateinit var inputFechaIncorporacion: TextField
    @FXML private lateinit var inputNotaMedia: TextField
    @FXML private lateinit var inputFaltas: TextField
    @FXML private lateinit var inputRetrasos: TextField
    @FXML private lateinit var inputPartes: TextField

    @FXML private lateinit var btnGuardarCambios: Button
    @FXML private lateinit var btnEliminarAlumno: Button

    // Guardamos la referencia del archivo CSV cargado
    private var archivoActual: File? = null

    @FXML
    fun initialize() {
        colId.setCellValueFactory { javafx.beans.property.SimpleIntegerProperty(it.value.id) }
        colNombre.setCellValueFactory { javafx.beans.property.SimpleStringProperty(it.value.nombre) }
        colApellidos.setCellValueFactory { javafx.beans.property.SimpleStringProperty(it.value.apellidos) }
        colEdad.setCellValueFactory { javafx.beans.property.SimpleIntegerProperty(it.value.edad) }
        colNotaMedia.setCellValueFactory { javafx.beans.property.SimpleDoubleProperty(it.value.notaMedia) }
        colFechaNacimiento.setCellValueFactory { javafx.beans.property.SimpleStringProperty(it.value.fechaNacimiento) }
        colNacionalidad.setCellValueFactory { javafx.beans.property.SimpleStringProperty(it.value.nacionalidad) }
        colFechaIncorporacion.setCellValueFactory { javafx.beans.property.SimpleStringProperty(it.value.fechaIncorporacion) }
        colFaltas.setCellValueFactory { javafx.beans.property.SimpleIntegerProperty(it.value.faltas) }
        colRetrasos.setCellValueFactory { javafx.beans.property.SimpleIntegerProperty(it.value.retrasos) }
        colPartes.setCellValueFactory { javafx.beans.property.SimpleIntegerProperty(it.value.partes) }

        tablaAlumnos.items = viewModel.alumnos

        tablaAlumnos.selectionModel.selectedItemProperty().addListener { _, _, alumno ->
            alumno?.let { mostrarAlumno(it) }
        }
    }

    private fun mostrarAlumno(alumno: Alumno) {
        inputNombre.text = alumno.nombre
        inputApellidos.text = alumno.apellidos
        inputEdad.text = alumno.edad.toString()
        inputFechaNacimiento.text = alumno.fechaNacimiento
        inputNacionalidad.text = alumno.nacionalidad
        inputFechaIncorporacion.text = alumno.fechaIncorporacion
        inputNotaMedia.text = alumno.notaMedia.toString()
        inputFaltas.text = alumno.faltas.toString()
        inputRetrasos.text = alumno.retrasos.toString()
        inputPartes.text = alumno.partes.toString()
    }

    @FXML
    fun onGuardarCambiosClicked() {
        val alumnoSeleccionado = tablaAlumnos.selectionModel.selectedItem
        if (alumnoSeleccionado != null) {
            val nuevosDatos = Alumno(
                id = alumnoSeleccionado.id,
                nombre = inputNombre.text,
                apellidos = inputApellidos.text,
                edad = inputEdad.text.toIntOrNull() ?: alumnoSeleccionado.edad,
                fechaNacimiento = inputFechaNacimiento.text,
                nacionalidad = inputNacionalidad.text,
                fechaIncorporacion = inputFechaIncorporacion.text,
                modulos = alumnoSeleccionado.modulos,
                expediente = alumnoSeleccionado.expediente,
                notaMedia = inputNotaMedia.text.toDoubleOrNull() ?: alumnoSeleccionado.notaMedia,
                faltas = inputFaltas.text.toIntOrNull() ?: alumnoSeleccionado.faltas,
                retrasos = inputRetrasos.text.toIntOrNull() ?: alumnoSeleccionado.retrasos,
                partes = inputPartes.text.toIntOrNull() ?: alumnoSeleccionado.partes
            )
            viewModel.actualizarAlumno(alumnoSeleccionado, nuevosDatos)
            tablaAlumnos.refresh()

            // Guardar automáticamente en archivo actual
            archivoActual?.let {
                val result = viewModel.guardarAlumnosEnArchivo(it)
                if (result.isOk) {
                    logger.debug { "✅ Cambios guardados en archivo CSV." }
                } else {
                    logger.debug { "✅ Cambios guardados en archivo CSV." }
                }
            }
        }
    }

    @FXML
    fun onEliminarAlumnoClicked() {
        val alumnoSeleccionado = tablaAlumnos.selectionModel.selectedItem
        if (alumnoSeleccionado != null) {
            val confirmacion = Alert(Alert.AlertType.CONFIRMATION).apply {
                title = "Confirmar eliminación"
                headerText = "Eliminar alumno"
                contentText = "¿Seguro que quieres eliminar al alumno seleccionado?"
            }.showAndWait()

            if (confirmacion.isPresent && confirmacion.get() == ButtonType.OK) {
                tablaAlumnos.items.remove(alumnoSeleccionado)

                archivoActual?.let {
                    val result = viewModel.guardarAlumnosEnArchivo(it)
                    if (result.isOk) {
                        logger.debug { "✅ Alumno eliminado y CSV actualizado correctamente." }
                    } else {
                        logger.error { "❌ Error al guardar CSV tras eliminación: ${result.error.message}" }
                    }
                } ?: run {
                    println("No hay archivo CSV cargado para guardar los cambios.")
                }
            }
        } else {
            logger.warn { "⚠️ No hay alumno seleccionado para eliminar." }
        }
    }

    @FXML
    private fun onAcercaDeClicked() {
        viewModel.abrirAcercaDe(RoutesManager.activeStage)
    }

    @FXML
    fun onImportarAlumnosClicked() {
        val fileChooser = FileChooser().apply {
            title = "Selecciona archivo CSV de alumnos"
            extensionFilters.add(FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"))
        }
        val selectedFile = fileChooser.showOpenDialog(RoutesManager.activeStage)
        if (selectedFile != null) {
            val resultado = viewModel.cargarAlumnosDesdeArchivo(selectedFile)
            if (resultado.isOk) {
                logger.debug { "✅ ${viewModel.alumnos.size} alumnos cargados desde archivo: ${selectedFile.name}" }
                archivoActual = selectedFile
            } else {
                logger.error { "❌ Error al importar alumnos: ${resultado.error.message}" }
            }
        } else {
            logger.warn { "⚠️ No se seleccionó ningún archivo para importar." }
        }
    }

    @FXML
    fun onExportarAlumnosClicked() {
        val fileChooser = FileChooser().apply {
            title = "Guardar archivo CSV de alumnos"
            extensionFilters.add(FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"))
        }
        val selectedFile = fileChooser.showSaveDialog(RoutesManager.activeStage)
        if (selectedFile != null) {
            val resultado = viewModel.guardarAlumnosEnArchivo(selectedFile)
            if (resultado.isOk) {
                logger.debug { "✅ Alumnos exportados correctamente al archivo: ${selectedFile.name}" }
            } else {
                logger.error { "❌ Error al exportar alumnos: ${resultado.error.message}" }
            }
        } else {
            logger.warn { "⚠️ No se seleccionó ningún archivo para guardar." }
        }
    }
    @FXML
    fun onBuscarAlumno() {
        val filtro = inputBuscar.text.trim()

        // condicional simple para mostrar todos los alumnos si no hay filtro
        if (filtro.isEmpty()) {
            tablaAlumnos.items = viewModel.alumnos
        } else {
            val filtroId = filtro.toIntOrNull()

            // condicional simple para no mostrar nada si no se pone un numero
            if (filtroId == null) {
                tablaAlumnos.items = FXCollections.observableArrayList()
            } else {
                val alumnosFiltrados = viewModel.alumnos.filter {
                    it.id == filtroId
                }
                tablaAlumnos.items = FXCollections.observableArrayList(alumnosFiltrados)
            }
        }
    }

    @FXML
    fun onCrearAlumno() {

        // se crea el alumno con los datos vacios
        val nuevoAlumno = Alumno(
            id = generarNuevoId(),
            nombre = "",
            apellidos = "",
            fechaNacimiento = "",
            edad = 0,
            nacionalidad = "",
            fechaIncorporacion = "",
            modulos = emptyList(),
            expediente = Expediente(emptyMap(), 0.0, ""),
            notaMedia = 0.0,
            faltas = 0,
            retrasos = 0,
            partes = 0
        )


        viewModel.alumnos.add(nuevoAlumno)


        tablaAlumnos.items = viewModel.alumnos
        tablaAlumnos.selectionModel.select(nuevoAlumno)

        mostrarAlumno(nuevoAlumno)

        logger.debug {"✅ Alumno con ID ${nuevoAlumno.id} creado con datos vacíos correctamente."}
    }

    private fun generarNuevoId(): Int {
        val maxId = viewModel.alumnos.maxOfOrNull { it.id } ?: 0
        return maxId + 1
    }
}
