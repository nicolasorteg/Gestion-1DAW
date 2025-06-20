package nicolasorteg.gestion1daw.controllers

import javafx.fxml.FXML
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager

class PantallaInicialController {
    @FXML
    private fun onAcercaDeClicked() {
        RoutesManager.initAcercaDeStage(RoutesManager.activeStage)
    }
}
