package nicolasorteg.gestion1daw.controllers

import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager
import nicolasorteg.gestion1daw.viewmodels.RegisterViewModel
import org.lighthousegames.logging.logging

class RegisterController {

    private val logger = logging()

    @FXML
    private lateinit var usernameTextField: TextField

    @FXML
    private lateinit var passwordField: PasswordField

    private val viewModel = RegisterViewModel()

    @FXML
    fun initialize() {
        usernameTextField.textProperty().bindBidirectional(viewModel.usernameProperty)
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty)
    }

    @FXML
    private fun onOkButtonClick() {
        if (!viewModel.validateCredentials()) {
            val alert = Alert(AlertType.WARNING)
            alert.title = "Campos vacíos" // titulo
            alert.headerText = null
            alert.contentText = "RELLENA TODOS LOS CAMPOS" // mensaje
            alert.showAndWait()
        } else {
            logger.debug { "✅ Registro finalizado con éxito" }
            println("Usuario: ${viewModel.getUsername()}")
            println("Contraseña: ${viewModel.getPassword()}")
            RoutesManager.initPantallaInicialStage(usernameTextField.scene.window as Stage)

        }
    }
}
