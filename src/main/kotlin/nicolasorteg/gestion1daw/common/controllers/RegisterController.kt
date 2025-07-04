package nicolasorteg.gestion1daw.common.controllers

import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Button
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager
import nicolasorteg.gestion1daw.common.viewmodels.RegisterViewModel
import org.lighthousegames.logging.logging
import org.mindrot.jbcrypt.BCrypt

/**
 * Controlador del registro.
 */
class RegisterController {

    private val logger = logging()

    @FXML
    private lateinit var usernameTextField: TextField

    @FXML
    private lateinit var passwordField: PasswordField

    @FXML
    lateinit var loginButton: Button

    private val viewModel = RegisterViewModel()

    @FXML
    fun initialize() {
        usernameTextField.textProperty().bindBidirectional(viewModel.usernameProperty)
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty)
        loginButton.setOnAction {
            RoutesManager.initLoginStage(usernameTextField.scene.window as Stage)
        }
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
            logger.debug { "Usuario: ${viewModel.getUsername()}, Contraseña: ${viewModel.getPassword()}" }
            RoutesManager.initPantallaInicialStage(usernameTextField.scene.window as Stage)

        }
    }
}
