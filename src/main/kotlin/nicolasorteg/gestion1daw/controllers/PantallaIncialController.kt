package nicolasorteg.gestion1daw.controllers

import javafx.fxml.FXML
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.Button
import nicolasorteg.gestion1daw.alumno.models.Alumno
import nicolasorteg.gestion1daw.alumno.storage.AlumnoStorageCsv
import nicolasorteg.gestion1daw.expediente.model.Expediente
import nicolasorteg.gestion1daw.routes.RoutesManager
import nicolasorteg.gestion1daw.viewmodels.PantallaInicialViewModel

class PantallaInicialController {

    private val viewModel = PantallaInicialViewModel()

    @FXML
    private lateinit var tablaAlumnos: javafx.scene.control.TableView<Alumno>

    @FXML
    private lateinit var colId: javafx.scene.control.TableColumn<Alumno, Number>

    @FXML
    private lateinit var colNombre: javafx.scene.control.TableColumn<Alumno, String>

    @FXML
    private lateinit var colApellidos: javafx.scene.control.TableColumn<Alumno, String>

    @FXML
    private lateinit var colEdad: javafx.scene.control.TableColumn<Alumno, Number>

    @FXML
    private lateinit var colNotaMedia: javafx.scene.control.TableColumn<Alumno, Number>

    @FXML
    private lateinit var inputNombre: TextField

    @FXML
    private lateinit var inputApellidos: TextField

    @FXML
    private lateinit var inputEdad: TextField

    @FXML
    private lateinit var inputFechaIncorporacion: TextField

    @FXML
    private lateinit var inputNotaMedia: TextField

    @FXML
    private lateinit var btnGuardarCambios: Button

    @FXML
    private lateinit var colFechaNacimiento: javafx.scene.control.TableColumn<Alumno, String>

    @FXML
    private lateinit var colNacionalidad: javafx.scene.control.TableColumn<Alumno, String>

    @FXML
    private lateinit var colFechaIncorporacion: javafx.scene.control.TableColumn<Alumno, String>

    @FXML
    private lateinit var colFaltas: javafx.scene.control.TableColumn<Alumno, Number>

    @FXML
    private lateinit var colRetrasos: javafx.scene.control.TableColumn<Alumno, Number>

    @FXML
    private lateinit var colPartes: javafx.scene.control.TableColumn<Alumno, Number>


    @FXML
    private lateinit var inputFechaNacimiento: TextField

    @FXML
    private lateinit var inputNacionalidad: TextField

    @FXML
    private lateinit var inputFaltas: TextField

    @FXML
    private lateinit var inputRetrasos: TextField

    @FXML
    private lateinit var inputPartes: TextField


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


        tablaAlumnos.items = javafx.collections.FXCollections.observableArrayList()

        tablaAlumnos.selectionModel.selectedItemProperty().addListener { _, _, alumno ->
            alumno?.let {
                mostrarAlumno(it)
            }
        }
    }

    private fun mostrarAlumno(alumno: Alumno) {
        inputNombre.text = alumno.nombre
        inputApellidos.text = alumno.apellidos
        inputEdad.text = alumno.edad.toString()
        inputFechaIncorporacion.text = alumno.fechaIncorporacion
        inputNotaMedia.text = alumno.notaMedia.toString()
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
            alumnoSeleccionado.nombre = inputNombre.text
            alumnoSeleccionado.apellidos = inputApellidos.text
            alumnoSeleccionado.edad = inputEdad.text.toIntOrNull() ?: alumnoSeleccionado.edad
            alumnoSeleccionado.fechaIncorporacion = inputFechaIncorporacion.text
            alumnoSeleccionado.notaMedia = inputNotaMedia.text.toDoubleOrNull() ?: alumnoSeleccionado.notaMedia
            alumnoSeleccionado.nombre = inputNombre.text
            alumnoSeleccionado.apellidos = inputApellidos.text
            alumnoSeleccionado.edad = inputEdad.text.toIntOrNull() ?: alumnoSeleccionado.edad
            alumnoSeleccionado.fechaNacimiento = inputFechaNacimiento.text
            alumnoSeleccionado.nacionalidad = inputNacionalidad.text
            alumnoSeleccionado.fechaIncorporacion = inputFechaIncorporacion.text
            alumnoSeleccionado.notaMedia = inputNotaMedia.text.toDoubleOrNull() ?: alumnoSeleccionado.notaMedia
            alumnoSeleccionado.faltas = inputFaltas.text.toIntOrNull() ?: alumnoSeleccionado.faltas
            alumnoSeleccionado.retrasos = inputRetrasos.text.toIntOrNull() ?: alumnoSeleccionado.retrasos
            alumnoSeleccionado.partes = inputPartes.text.toIntOrNull() ?: alumnoSeleccionado.partes


            tablaAlumnos.refresh()
        }
    }

    @FXML
    private fun onAcercaDeClicked() {
        viewModel.openAcercaDe(RoutesManager.activeStage)
    }


    @FXML
    private fun onImportarAlumnosClicked() {
        val fileChooser = javafx.stage.FileChooser()
        fileChooser.title = "Selecciona archivo CSV de alumnos"
        fileChooser.extensionFilters.add(
            javafx.stage.FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv")
        )

        val selectedFile = fileChooser.showOpenDialog(RoutesManager.activeStage)

        if (selectedFile != null) {
            val storage = AlumnoStorageCsv()
            val result = storage.readFromFile(selectedFile)

            if (result.isOk) {
                val alumnos = result.value
                tablaAlumnos.items.clear()
                tablaAlumnos.items.addAll(alumnos)
                println("✅ ${alumnos.size} alumnos cargados en la tabla.")
            } else {
                println("Error al importar alumnos: ${result.error.message}")
            }
        } else {
            println("❗ No se seleccionó ningún archivo.")
        }
    }

}
