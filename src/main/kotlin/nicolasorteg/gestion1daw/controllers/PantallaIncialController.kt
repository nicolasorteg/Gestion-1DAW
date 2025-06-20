package nicolasorteg.gestion1daw.controllers

import javafx.fxml.FXML
import nicolasorteg.gestion1daw.routes.RoutesManager
import nicolasorteg.gestion1daw.viewmodels.PantallaInicialViewModel

class PantallaInicialController {

    private val viewModel = PantallaInicialViewModel()

    @FXML
    private fun onAcercaDeClicked() {
        viewModel.openAcercaDe(RoutesManager.activeStage)
    }
}
