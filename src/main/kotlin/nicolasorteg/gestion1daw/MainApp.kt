package nicolasorteg.gestion1daw

import javafx.application.Application
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager

class MainApp : Application() {

    override fun start(primaryStage: Stage) {
        RoutesManager.apply {
            app = this@MainApp
        }.run {
            initSplashStage(primaryStage)
        }
    }

    override fun stop() {
        println("On Application Stop")
    }

    override fun init() {
        println("On Application Init")
    }
}

fun main() {
    Application.launch(MainApp::class.java)
}