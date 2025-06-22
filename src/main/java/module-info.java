module nicolasorteg.gestion1daw {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlin.result.jvm;

    // Logger
    requires logging.jvm;
    requires org.slf4j;
    requires java.desktop;
    requires org.jdbi.v3.sqlobject.kotlin;
    requires org.jdbi.v3.sqlobject;
    requires org.jdbi.v3.core;

    opens nicolasorteg.gestion1daw to javafx.fxml;
    opens nicolasorteg.gestion1daw.controllers to javafx.fxml;

    exports nicolasorteg.gestion1daw;
    exports nicolasorteg.gestion1daw.controllers;
    exports nicolasorteg.gestion1daw.viewmodels;
    exports nicolasorteg.gestion1daw.alumno.models;
    exports nicolasorteg.gestion1daw.expediente.model;
}
