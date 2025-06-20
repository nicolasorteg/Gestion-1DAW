package nicolasorteg.gestion1daw.viewmodels

import javafx.beans.property.SimpleStringProperty

class RegisterViewModel {
    val usernameProperty = SimpleStringProperty("")
    val passwordProperty = SimpleStringProperty("")

    fun validateCredentials(): Boolean {
        return usernameProperty.get().isNotBlank() && passwordProperty.get().isNotBlank()
    }

    fun getUsername(): String = usernameProperty.get()
    fun getPassword(): String = passwordProperty.get()
}
