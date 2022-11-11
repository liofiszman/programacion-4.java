package com.programacion.cuatro;

import com.programacion.cuatro.Business.TurnoBusinessObject;
import com.programacion.cuatro.Controllers.ReportesController;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Utils {

    @Autowired
    private static TurnoBusinessObject turnosBO;


        public static TurnoBusinessObject getTurnosBO(){
           if(turnosBO == null)
               turnosBO = new TurnoBusinessObject();

            return turnosBO;
        }



        public static void reporteDiario(Stage stage) throws IOException {
            ReportesController.diario(stage);
        }

        public static void reporteMensual(Stage stage) throws IOException {
            ReportesController.mensual(stage);
        }



}
