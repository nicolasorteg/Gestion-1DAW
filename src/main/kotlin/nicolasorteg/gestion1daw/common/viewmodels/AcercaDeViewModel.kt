package nicolasorteg.gestion1daw.common.viewmodels

import java.awt.Desktop
import java.net.URI

class AcercaDeViewModel {
    fun openGithub(url: String) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI(url))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}