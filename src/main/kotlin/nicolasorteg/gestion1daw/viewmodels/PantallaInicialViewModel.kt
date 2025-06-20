package nicolasorteg.gestion1daw.viewmodels

import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager

class PantallaInicialViewModel {
    fun openAcercaDe(stage: Stage) {
        RoutesManager.initAcercaDeStage(stage)
    }
}
