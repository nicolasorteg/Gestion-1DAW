package nicolasorteg.gestion1daw.controllers

import javafx.fxml.FXML
import javafx.scene.input.MouseEvent
import nicolasorteg.gestion1daw.viewmodels.AcercaDeViewModel

class AcercaDeController {

    private val viewModel = AcercaDeViewModel()

    @FXML
    private fun onGithubClicked(event: MouseEvent) {
        viewModel.openGithub("https://github.com/nicolasorteg")
    }
}
