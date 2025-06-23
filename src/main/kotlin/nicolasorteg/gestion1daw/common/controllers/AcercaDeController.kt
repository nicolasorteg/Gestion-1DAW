package nicolasorteg.gestion1daw.common.controllers

import javafx.fxml.FXML
import javafx.scene.input.MouseEvent
import nicolasorteg.gestion1daw.common.viewmodels.AcercaDeViewModel
import org.lighthousegames.logging.logging

/**
 * Controlador del Acerca de
 */
class AcercaDeController {

    private val logger = logging()
    private val viewModel = AcercaDeViewModel()

    @FXML
    private fun onGithubClicked(event: MouseEvent) {
        viewModel.openGithub("https://github.com/nicolasorteg")
        logger.debug { "🔵 Redirigiendo al repositorio..." }

    }
}
