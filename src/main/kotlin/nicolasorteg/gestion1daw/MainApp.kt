package nicolasorteg.gestion1daw

import javafx.application.Application
import javafx.stage.Stage
import nicolasorteg.gestion1daw.di.appModule
import nicolasorteg.gestion1daw.routes.RoutesManager
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

/**
 * main
 */
class MainApp: Application() {

    init {
        // creacion Koin
        startKoin {
            printLogger() // logger de Koin
            modules(appModule) // modulos de Koin
        }
    }
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