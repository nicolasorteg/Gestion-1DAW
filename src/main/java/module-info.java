module nicolasorteg.gestion1daw {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlin.result.jvm;


    opens nicolasorteg.gestion1daw to javafx.fxml;
    opens nicolasorteg.gestion1daw.controllers to javafx.fxml;

    exports nicolasorteg.gestion1daw;
    exports nicolasorteg.gestion1daw.controllers to javafx.fxml;
}