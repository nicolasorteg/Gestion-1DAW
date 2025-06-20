package nicolasorteg.gestion1daw.controllers

import javafx.fxml.FXML
import javafx.scene.input.MouseEvent
import java.awt.Desktop
import java.net.URI

class AcercaDeController {

    @FXML
    private fun onGithubClicked(event: MouseEvent) {
        val url = "https://github.com/nicolasorteg"
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI(url))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
