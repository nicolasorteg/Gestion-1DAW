module nicolasorteg.gestion1daw {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens nicolasorteg.gestion1daw to javafx.fxml;
    exports nicolasorteg.gestion1daw;
}