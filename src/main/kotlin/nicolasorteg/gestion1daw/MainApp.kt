package nicolasorteg.gestion1daw

import javafx.application.Application
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager

/**
 * main
 */
class MainApp: Application() {

    override fun start(primaryStage: Stage) {
        RoutesManager.apply {
            app = this@MainApp
        }.run {
            initSplashStage(primaryStage)
        }
    }

    /**
     * muestra el msg de inicio
     */
    override fun init() {
        println("-- INICIO DE LA APLICACIÓN --")
    }

    /**
     * muestra msg si se detiene
     */
    override fun stop() {
        println("-- APLICACIÓN DETENIDA--")
    }
}

fun main() {
    Application.launch(MainApp::class.java)
}