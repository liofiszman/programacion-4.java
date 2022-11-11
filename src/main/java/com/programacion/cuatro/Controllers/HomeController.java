package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.ProgramacionFourApplication;
import com.programacion.cuatro.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@FxmlView("/Views/home-view.fxml")
public class HomeController {
    @FXML
    private Label welcomeText;

    @Autowired
    private ConfigurableApplicationContext applicationContext;


    @FXML
    protected void onRegistrarCalendarioButtonClick(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,TurnosController.class));
        /*MainApplication.calendario(stage);*/
    }

    @FXML
    private AnchorPane showPane;
    @FXML
    protected void onReporteDiarioButtonClick(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Utils.reporteDiario(stage);
    }
    @FXML
    protected void onReporteMensualButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Utils.reporteMensual(stage);
    }
    @FXML
    protected void onRegistrarAsistenciaButtonClick(ActionEvent event){

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,AsistenciaController.class));

    }
    @FXML
    protected void onRegistrarActividadesButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,ActividadesController.class));
    }

    @FXML
    protected void onRegistrarConformidadButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,ConformidadController.class));
    }


    @FXML
    protected void onObtenerReporteMensualButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Utils.reporteMensual(stage);
    }

    @FXML
    protected void onBuscarTurnosButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,BuscadorController.class));

    }

}
