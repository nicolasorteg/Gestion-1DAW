package nicolasorteg.gestion1daw.routes

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.lighthousegames.logging.logging
import java.io.InputStream
import java.net.URL

object RoutesManager {
    private lateinit var mainStage: Stage // Escena principal de la app
    private lateinit var _activeStage: Stage // Escena cargada actualmente en la vista
    val activeStage: Stage
        get() = _activeStage
    lateinit var app: Application

    val logger = logging()

    enum class Vistas(val path: String) {
        SPLASH("views/splash-screen-view.fxml"),
        REGISTER("views/register-view.fxml"),
        PANTALLA_INICIAL("views/pantalla-principal-view.fxml"),
        ACERCA_DE("views/acerca-de-view.fxml")
    }

    fun initSplashStage(stage: Stage) {
        logger.debug { "Iniciando splash screen" }
        val fxmlLoader = FXMLLoader(getResource(Vistas.SPLASH.path))
        val parentRoot = fxmlLoader.load<Pane>()
        val myScene = Scene(parentRoot, 778.0, 530.0)
        stage.title = "Cargando..."
        stage.scene = myScene
        stage.centerOnScreen()
        stage.icons.add(Image(getResourceAsStream("media/app-icon.png")))
        stage.isResizable = false
        mainStage = stage
        _activeStage = stage
        mainStage.show()
    }

    fun initRegisterStage(stage: Stage){
        logger.debug { "Iniciando register stage" }
        val fxmlLoader = FXMLLoader(getResource(Vistas.REGISTER.path))
        val parentRoot = fxmlLoader.load<Pane>()
        val myScene = Scene(parentRoot, 778.0, 530.0)
        stage.title = "Registro"
        stage.isResizable = false
        stage.icons.add(Image(getResourceAsStream("media/app-icon.png")))
        stage.scene = myScene
        stage.centerOnScreen()
        stage.setOnCloseRequest {  }
        mainStage = stage
        _activeStage = stage
        mainStage.show()
    }

    fun initPantallaInicialStage(stage: Stage) {
        logger.debug { "Iniciando pantalla principal" }
        val fxmlLoader = FXMLLoader(getResource(Vistas.PANTALLA_INICIAL.path))
        val parentRoot = fxmlLoader.load<Pane>()
        val scene = Scene(parentRoot, 1000.0, 700.0)
        stage.title = "Pantalla Principal"
        stage.scene = scene
        stage.centerOnScreen()
        stage.icons.add(Image(getResourceAsStream("media/app-icon.png")))
        stage.isResizable = false
        _activeStage = stage
        mainStage = stage
        stage.show()
    }

    fun initAcercaDeStage(owner: Stage) {
        val fxmlLoader = FXMLLoader(getResource(Vistas.ACERCA_DE.path))
        val root = fxmlLoader.load<Pane>()
        val stage = Stage()

        stage.title = "Acerca de"
        stage.scene = Scene(root, 390.0, 310.0)
        stage.initOwner(owner)
        stage.isResizable = false
        stage.icons.add(Image(getResourceAsStream("media/app-icon.png")))
        stage.show()
    }

    fun getResource(resource: String): URL {
        return app::class.java.getResource(resource)
            ?: throw RuntimeException("Recurso no encontrado: $resource")
    }

    fun getResourceAsStream(resource: String): InputStream {
        return app::class.java.getResourceAsStream(resource)
            ?: throw RuntimeException("Recurso no encontrado como stream: $resource")
    }
}