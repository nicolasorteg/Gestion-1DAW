package nicolasorteg.gestion1daw.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager
import nicolasorteg.gestion1daw.viewmodels.SplashScreenViewModel

class SplashScreenController {
    @FXML
    lateinit var progressBar: ProgressBar
    @FXML
    lateinit var mensajeCarga: Label

    private val viewModel = SplashScreenViewModel()

    @FXML
    fun initialize() {
        progressBar.progressProperty().bind(viewModel.progressProperty)
        mensajeCarga.textProperty().bind(viewModel.mensajeProperty)

        viewModel.startLoading {
            RoutesManager.initRegisterStage(progressBar.scene.window as Stage)
        }
    }
}
