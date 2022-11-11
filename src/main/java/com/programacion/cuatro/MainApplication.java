package com.programacion.cuatro;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MainApplication {

/*
    @Override
    public void start(Stage stage) throws IOException {
        home(stage);
    }

 */

    public static void home(Stage stage) throws IOException {
        showStage(stage, "/Views/home-view.fxml");
    }


    public static void showStage(Stage stage, String viewName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(viewName));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Sistema de turnos para taller mecánico");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        Application.launch(ProgramacionFourApplication.class, args);
    }


}
