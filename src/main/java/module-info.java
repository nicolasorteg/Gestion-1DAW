module nicolasorteg.gestion1daw {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    // result
    requires kotlin.result.jvm;

    // Logger
    requires logging.jvm;
    requires org.slf4j;

    requires java.desktop;

    // jdbi
    requires org.jdbi.v3.sqlobject.kotlin;
    requires org.jdbi.v3.sqlobject;
    requires org.jdbi.v3.core;


    opens nicolasorteg.gestion1daw.alumno.repository to org.mockito.mockito.core, io.mockk;
    opens nicolasorteg.gestion1daw.alumno.service to org.mockito.mockito.core, io.mockk;

    opens nicolasorteg.gestion1daw to javafx.fxml;
    opens nicolasorteg.gestion1daw.common.controllers to javafx.fxml;

    exports nicolasorteg.gestion1daw;
    exports nicolasorteg.gestion1daw.common.controllers;
    exports nicolasorteg.gestion1daw.common.viewmodels;
    exports nicolasorteg.gestion1daw.alumno.models;
    exports nicolasorteg.gestion1daw.expediente.model;
}