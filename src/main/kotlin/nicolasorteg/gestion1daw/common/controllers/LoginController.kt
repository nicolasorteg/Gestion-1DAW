package nicolasorteg.gestion1daw.common.controllers

import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager
import org.mindrot.jbcrypt.BCrypt

class LoginController {

    @FXML
    private lateinit var usernameTextField: TextField

    @FXML
    private lateinit var passwordField: PasswordField

    @FXML
    private lateinit var volverRegistroButton: Button

    @FXML
    fun initialize() {
        volverRegistroButton.setOnAction {
            RoutesManager.initRegisterStage(usernameTextField.scene.window as Stage)
        }
    }

    @FXML
    private fun onOkButtonClick() {
        val username = usernameTextField.text.trim()
        val password = passwordField.text.trim()

        // usuario válido: admin / contra123 (hash bcrypt a verificar)
        val hashedPassword = BCrypt.hashpw("contra123", BCrypt.gensalt())

        if (username == "admin" && BCrypt.checkpw(password, hashedPassword)) {
            RoutesManager.initPantallaInicialStage(usernameTextField.scene.window as Stage)
        } else {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Error de Inicio de Sesión"
            alert.headerText = null
            alert.contentText = "Credenciales incorrectas"
            alert.showAndWait()
        }
    }
}
