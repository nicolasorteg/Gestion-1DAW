package nicolasorteg.gestion1daw.common.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.stage.FileChooser
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.routes.RoutesManager
import nicolasorteg.gestion1daw.common.viewmodels.PantallaInicialViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.lighthousegames.logging.logging
import java.io.File
import javafx.scene.control.cell.PropertyValueFactory

class PantallaInicialController(): KoinComponent {

    private val logger = logging()
    private val viewModel: PantallaInicialViewModel by inject()

    @FXML
    private lateinit var tablaAlumnos: TableView<Alumno>

    @FXML
    private lateinit var colId: TableColumn<Alumno, Number>
    @FXML
    private lateinit var colNombre: TableColumn<Alumno, String>
    @FXML
    private lateinit var colApellidos: TableColumn<Alumno, String>
    @FXML
    private lateinit var colEdad: TableColumn<Alumno, Number>
    @FXML
    private lateinit var colNotaMedia: TableColumn<Alumno, Number>
    @FXML
    private lateinit var colFechaNacimiento: TableColumn<Alumno, String>
    @FXML
    private lateinit var colNacionalidad: TableColumn<Alumno, String>
    @FXML
    private lateinit var colFechaIncorporacion: TableColumn<Alumno, String>
    @FXML
    private lateinit var colFaltas: TableColumn<Alumno, Number>
    @FXML
    private lateinit var colRetrasos: TableColumn<Alumno, Number>
    @FXML
    private lateinit var colPartes: TableColumn<Alumno, Number>
    @FXML
    private lateinit var inputBuscar: TextField
    @FXML
    private lateinit var inputNombre: TextField
    @FXML
    private lateinit var inputApellidos: TextField
    @FXML
    private lateinit var inputEdad: TextField
    @FXML
    private lateinit var inputFechaNacimiento: TextField
    @FXML
    private lateinit var inputNacionalidad: TextField
    @FXML
    private lateinit var inputFechaIncorporacion: TextField
    @FXML
    private lateinit var inputNotaMedia: TextField
    @FXML
    private lateinit var inputFaltas: TextField
    @FXML
    private lateinit var inputRetrasos: TextField
    @FXML
    private lateinit var inputPartes: TextField

    @FXML
    private lateinit var btnGuardarCambios: Button
    @FXML
    private lateinit var btnEliminarAlumno: Button

    private var archivoActual: File? = null

    @FXML
    fun initialize() {
        colId.cellValueFactory = PropertyValueFactory("id")
        colNombre.cellValueFactory = PropertyValueFactory("nombre")
        colApellidos.cellValueFactory = PropertyValueFactory("apellidos")
        colEdad.cellValueFactory = PropertyValueFactory("edad")
        colNotaMedia.cellValueFactory = PropertyValueFactory("notaMedia")
        colFechaNacimiento.cellValueFactory = PropertyValueFactory("fechaNacimiento")
        colNacionalidad.cellValueFactory = PropertyValueFactory("nacionalidad")
        colFechaIncorporacion.cellValueFactory = PropertyValueFactory("fechaIncorporacion")
        colFaltas.cellValueFactory = PropertyValueFactory("faltas")
        colRetrasos.cellValueFactory = PropertyValueFactory("retrasos")
        colPartes.cellValueFactory = PropertyValueFactory("partes")

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

            val resultado = viewModel.actualizarAlumno(alumnoSeleccionado, nuevosDatos)
            if (resultado.isOk) {
                tablaAlumnos.refresh()

                archivoActual?.let {
                    val saveResult = viewModel.guardarAlumnosEnArchivo(it)
                    if (saveResult.isOk) {
                        logger.debug { "✅ Cambios guardados en archivo CSV." }
                    } else {
                        logger.error { "❌ Error guardando archivo CSV: ${saveResult.error.message}" }
                    }
                }
            } else {
                val errorAlert = Alert(Alert.AlertType.ERROR).apply {
                    title = "Error al actualizar"
                    headerText = "No se pudo actualizar el alumno"
                    contentText = resultado.error.message
                }
                errorAlert.showAndWait()
                logger.error { "❌ Error actualizando alumno: ${resultado.error.message}" }
            }
        } else {
            val advertencia = Alert(Alert.AlertType.WARNING).apply {
                title = "Advertencia"
                headerText = "No se ha seleccionado ningún alumno"
                contentText = "Por favor, selecciona un alumno antes de guardar los cambios."
            }
            advertencia.showAndWait()
            logger.warn { "⚠️ No hay alumno seleccionado para guardar cambios." }
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
                val eliminado = viewModel.eliminarAlumno(alumnoSeleccionado)
                if (eliminado) {
                    tablaAlumnos.refresh()
                    archivoActual?.let {
                        val result = viewModel.guardarAlumnosEnArchivo(it)
                        if (result.isOk) {
                            logger.debug { "✅ Alumno eliminado y CSV actualizado correctamente." }
                        } else {
                            logger.error { "❌ Error al guardar CSV tras eliminación: ${result.error.message}" }
                        }
                    }
                }
            }
        } else {
            val advertencia = Alert(Alert.AlertType.WARNING).apply {
                title = "Advertencia"
                headerText = "No se ha seleccionado ningún alumno"
                contentText = "Por favor, selecciona un alumno antes de eliminar."
            }
            advertencia.showAndWait()
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

        if (filtro.isEmpty()) {
            tablaAlumnos.items = viewModel.alumnos
        } else {
            val filtroId = filtro.toIntOrNull()
            if (filtroId == null) {
                tablaAlumnos.items = FXCollections.observableArrayList()
            } else {
                val alumnosFiltrados = viewModel.alumnos.filter { it.id == filtroId }
                tablaAlumnos.items = FXCollections.observableArrayList(alumnosFiltrados)
            }
        }
    }

    @FXML
    fun onCrearAlumno() {
        val nuevoAlumno = viewModel.crearAlumno()
        tablaAlumnos.selectionModel.select(nuevoAlumno)
        mostrarAlumno(nuevoAlumno)
        logger.debug { "✅ Alumno con ID ${nuevoAlumno.id} creado con datos vacíos correctamente." }
    }
}
