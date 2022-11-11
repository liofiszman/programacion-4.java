package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.Business.ReporteStrategy.TipoReporteEnum;
import com.programacion.cuatro.Business.TurnoBusinessObject;
import com.programacion.cuatro.Classes.Arreglo;
import com.programacion.cuatro.Entities.TurnoEntity;
import com.programacion.cuatro.ProgramacionFourApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableList;
@Component
public class ReportesController extends BaseController {


    private static TurnoBusinessObject turnoBusinessObject;


    private static ConfigurableApplicationContext applicationContext;

    @Autowired
    public ReportesController(TurnoBusinessObject turnoBusinessObject,  ConfigurableApplicationContext applicationContext) {
        ReportesController.turnoBusinessObject = turnoBusinessObject;
        ReportesController.applicationContext = applicationContext;
    }


    public static void diario(Stage stage) throws IOException {
        TableView<Arreglo> table = new TableView<Arreglo>();
        var turnos = turnoBusinessObject.GetTurnos(TipoReporteEnum.Diario);
        ObservableList<Arreglo> arreglos = getArreglos(turnos);
        table.setItems(arreglos);

        //Creating columns
        TableColumn clienteCol = new TableColumn("Patente");
        clienteCol.setCellValueFactory(new PropertyValueFactory<>("patente"));
        TableColumn mecanicoCol = new TableColumn("Mecanico");
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanico"));
        TableColumn especialidadCol = new TableColumn("Especialidad");
        especialidadCol.setCellValueFactory(new PropertyValueFactory("especialidad"));
        TableColumn conformidadCol = new TableColumn("Conformidad");
        conformidadCol.setCellValueFactory(new PropertyValueFactory("conformidad"));
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanico"));
        TableColumn servicioCol = new TableColumn("Servicio");
        servicioCol.setCellValueFactory(new PropertyValueFactory("servicio"));
        table.getColumns().addAll(clienteCol, mecanicoCol, especialidadCol, conformidadCol, servicioCol);

        VBox vbox = new VBox(table);
        vbox.getChildren().add(getBackButton());
        Scene scene = new Scene(vbox, 800, 600);

        stage.setScene(scene);

        stage.show();
    }

    private static Button getBackButton() {
        var backButton = new Button();
        backButton.setAlignment(Pos.BOTTOM_CENTER);

        backButton.setText("X");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,HomeController.class));

            }
        });

        return backButton;
    }

    private static ObservableList<Arreglo> getArreglos(List<TurnoEntity> turnos) {
        List<Arreglo> arreglos = new ArrayList<>();
        for(TurnoEntity turno :turnos) {
               arreglos.add(new Arreglo(turno, turno.getMecanico(), turno.getFichaMecanica().getFichaConformidad(), turno.getFichaMecanica(), turno.getVehiculo(), turno.getVehiculo().getCompaniaSeguro()));
        }

        return observableList(arreglos);
    }

    public static void mensual(Stage stage) throws IOException {
        TableView<Arreglo> table = new TableView<Arreglo>();
        var turnos = turnoBusinessObject.GetTurnos(TipoReporteEnum.Mensual);
        ObservableList<Arreglo> arreglos = getArreglos(turnos);
        table.setItems(arreglos);

        //Creating columns
        TableColumn companiaCol = new TableColumn("Aseguradora");
        companiaCol.setCellValueFactory(new PropertyValueFactory<>("compania"));
        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        TableColumn clienteCol = new TableColumn("Patente");
        clienteCol.setCellValueFactory(new PropertyValueFactory<>("patente"));
        TableColumn mecanicoCol = new TableColumn("Mecanico");
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanico"));
        TableColumn servicioCol = new TableColumn("Servicio");
        servicioCol.setCellValueFactory(new PropertyValueFactory("servicio"));
        TableColumn conformidadCol = new TableColumn("Conformidad");
        conformidadCol.setCellValueFactory(new PropertyValueFactory("conformidad"));
        table.getColumns().addAll(companiaCol, fechaCol, clienteCol, mecanicoCol, servicioCol, conformidadCol);

        VBox vbox = new VBox(table);
        vbox.getChildren().add(getBackButton());
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
}
