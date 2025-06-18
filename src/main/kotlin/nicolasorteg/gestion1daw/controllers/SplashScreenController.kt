package nicolasorteg.gestion1daw.controllers

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.stage.Stage
import nicolasorteg.gestion1daw.routes.RoutesManager

import kotlin.concurrent.thread

class SplashScreenController {
    @FXML
    lateinit var progressBar: ProgressBar
    @FXML
    lateinit var mensajeCarga: Label

    private var isFinished = false

    fun initialize() {
        mensajeCarga.text = "Cargando... 0%"
        thread { isFinished = functionsInit() }
        initEvents()
    }

    private fun initEvents(){
        progressBar.progressProperty().addListener { _, _, newValue ->
            if (newValue == 1.0) RoutesManager.initRegisterStage(progressBar.scene.window as Stage)
        }
    }

    private fun functionsInit(): Boolean {
        progressBar.progress = 0.0
        for (i in 0..100){
            val progress = i / 100.0
            Platform.runLater {
                progressBar.progress = progress
                mensajeCarga.text = "Cargando... $i %"

            }
            Thread.sleep(50)
        }
        return true
    }
}