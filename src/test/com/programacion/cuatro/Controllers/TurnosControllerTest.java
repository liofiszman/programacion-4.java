package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.ProgramacionFourApplication;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.service.query.NodeQuery;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.testfx.api.FxAssert.verifyThat;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class TurnosControllerTest {

    @Autowired
    private ConfigurableApplicationContext applicationContext;



    @Start
    public void start(Stage stage) throws IOException {

        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,TurnosController.class));
    }


    @Test
    //Verifico que se selecciono con una especialidad
    void datosTurnoButtonClick_ConfirmaSinEspecialidad(FxRobot robot) {
        robot.clickOn("#PatenteTextField").write("HJC 2020");
        robot.clickOn("#ClienteTextField").write("Juan Perez");
        robot.moveTo("#ButtonVerOpciones");
        verifyThat("#especialidadCombo", (ComboBox c)-> c.getSelectionModel().selectedItemProperty().toString().trim().isEmpty());
    }





}
