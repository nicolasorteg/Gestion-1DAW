module nicolasorteg.gestion1daw {

    // requisitos base
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.desktop;

    // result
    requires kotlin.result.jvm;

    // Logger
    requires logging.jvm;
    requires org.slf4j;

    // jdbi
    requires org.jdbi.v3.sqlobject.kotlin;
    requires org.jdbi.v3.sqlobject;
    requires org.jdbi.v3.core;
    requires org.jdbi.v3.kotlin;

    //koin
    requires koin.core.jvm;
    requires io.leangen.geantyref;

    //bcrypt
    requires jbcrypt;

    requires java.sql;
    requires com.h2database;
    requires kotlin.reflect;


    // fxml
    opens nicolasorteg.gestion1daw to javafx.fxml;
    opens nicolasorteg.gestion1daw.common.controllers to javafx.fxml;

    exports nicolasorteg.gestion1daw;
    exports nicolasorteg.gestion1daw.common.controllers;
    exports nicolasorteg.gestion1daw.common.viewmodels;
    exports nicolasorteg.gestion1daw.alumno.models;
    exports nicolasorteg.gestion1daw.expediente.model;
}