package nicolasorteg.gestion1daw.common.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager
import nicolasorteg.gestion1daw.common.viewmodels.SplashScreenViewModel
import org.lighthousegames.logging.logging

class SplashScreenController {

    private val logger = logging()

    @FXML
    lateinit var progressBar: ProgressBar
    @FXML
    lateinit var mensajeCarga: Label

    private val viewModel = SplashScreenViewModel()

    @FXML
    fun initialize() {
        logger.debug { "🔵 Cargando aplicación..." }
        progressBar.progressProperty().bind(viewModel.progressProperty)
        mensajeCarga.textProperty().bind(viewModel.mensajeProperty)

        viewModel.startLoading {
            logger.debug { "✅ Carga completada. Abriendo registro..." }
            RoutesManager.initRegisterStage(progressBar.scene.window as Stage)
        }
    }
}
