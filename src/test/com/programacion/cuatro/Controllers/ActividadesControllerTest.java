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
import static org.testfx.api.FxAssert.verifyThat;


import java.io.IOException;

@SpringBootTest
@ExtendWith(ApplicationExtension.class)
class ActividadesControllerTest {

    @Autowired
    private ConfigurableApplicationContext applicationContext;




    @Start
    public void start(Stage stage) throws IOException {

        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,ActividadesController.class));
    }


    //Verifico que existe el turno
    @Test
    void datosTurnoButtonClick_TurnoExiste(FxRobot robot) {
        robot.clickOn("#TurnoTextField").write("104");
        robot.clickOn("#ButtonBuscar");
        verifyThat("#datosTurnoText", (Label l)-> !l.getText().contains("no encontrado"));
    }

    @Test
    //Verifico que no registre sin cargar actividades
    void datosTurnoButtonClick_ConfirmaSinTurno(FxRobot robot) {
        robot.clickOn("#TurnoTextField").write("120");
        robot.clickOn("#ButtonBuscar");
        robot.clickOn("#ButtonRegistrar");
        verifyThat("#actividadesTextField", (TextField l)-> !l.getText().isEmpty());
    }

}
