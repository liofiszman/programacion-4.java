package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.ProgramacionFourApplication;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class AsistenciaControllerTest {

    @Autowired
    private ConfigurableApplicationContext applicationContext;




    @Start
    public void start(Stage stage) throws IOException {

        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,AsistenciaController.class));
    }


    //Verifico que registra asistencia con un turno no confirmado
    @Test
    void verificarAsistenciaConTurno(FxRobot robot) {
        robot.clickOn("#TurnoTextField").write("104");
        robot.clickOn("#ButtonBuscar");
        robot.clickOn("#confirmarTurnoButton");
        verifyThat("#datosTurnoText", (Label l)-> !l.getText().contains("No se puede confirmar un turno con estado ASISTENCIA"));
    }


}
