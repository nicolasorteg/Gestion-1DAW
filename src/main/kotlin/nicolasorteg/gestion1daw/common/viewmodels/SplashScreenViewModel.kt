package nicolasorteg.gestion1daw.common.viewmodels

import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty

class SplashScreenViewModel {

    val progressProperty = SimpleDoubleProperty(0.0)
    val mensajeProperty = SimpleStringProperty("")

    fun startLoading(onFinished: () -> Unit) {
        Thread {
            for (i in 0..100) {
                val progress = i / 100.0
                Platform.runLater {
                    progressProperty.set(progress)
                    mensajeProperty.set("Cargando... $i %")
                }
                Thread.sleep(50)
            }
            Platform.runLater { onFinished() }
        }.start()
    }
}
