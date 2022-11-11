package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.MainApplication;
import com.programacion.cuatro.ProgramacionFourApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
@Controller
public abstract class BaseController implements Initializable {


    @Autowired
    private ConfigurableApplicationContext applicationContext;




    @FXML
    protected void backToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,HomeController.class));
    }

    protected static void backToHomeStatic(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainApplication.home(stage);
    }
}
