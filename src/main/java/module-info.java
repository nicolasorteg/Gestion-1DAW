module nicolasorteg.gestion1daw {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlin.result.jvm;

    // Logger
    requires logging.jvm;
    requires org.slf4j;


    opens nicolasorteg.gestion1daw to javafx.fxml;
    opens nicolasorteg.gestion1daw.controllers to javafx.fxml;

    exports nicolasorteg.gestion1daw;
    exports nicolasorteg.gestion1daw.controllers to javafx.fxml;
}