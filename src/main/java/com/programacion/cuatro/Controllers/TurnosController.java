package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.Business.TurnoBusinessObject;
import com.programacion.cuatro.Classes.Opcion;
import com.programacion.cuatro.Entities.MecanicoEntity;
import com.programacion.cuatro.Entities.TurnoEntity;
import com.programacion.cuatro.ProgramacionFourApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
@FxmlView("/Views/calendario-view.fxml")
@Controller
public class TurnosController extends BaseController {
    @Autowired
    private Opcion opcion;
    private String numeroTurno;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    TurnoBusinessObject turnoBusinessObject;

    /// Captura la información del formulario para solicitar turno
    @FXML
    protected void verOpcionesButtonClick(ActionEvent event) throws IOException {
        if(this.PatenteTextField.getText().trim().isEmpty()
                || this.ClienteTextField.getText().trim().isEmpty()
                || this.especialidadCombo.getSelectionModel().selectedItemProperty().toString().trim().isEmpty()
                || this.companiaCombo.getSelectionModel().selectedItemProperty().toString().trim().isEmpty()
                || this.FechaSeleccionada.getValue() == null
        ){
          Dialog dialog = new Dialog();
          dialog.setTitle("Error");
          DialogPane dialogPane = new DialogPane();
          dialogPane.setContentText("Complete todos los campos");
          dialogPane.getButtonTypes().addAll(ButtonType.OK);
          dialog.setDialogPane(dialogPane);

          dialog.show();
        }else {
            opcion.setPatente(this.PatenteTextField.getText());
            opcion.setCliente(this.ClienteTextField.getText());
            opcion.setEspecialidad(this.selectedEspecialidad.getText());
            opcion.setCompania(this.selectedCompania.getText());
            opcion.setFecha(this.FechaSeleccionada.getValue());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            verOpciones(stage);
        }
    }

    /// Lista las opciones de turnos en formato de tabla.
    public void verOpciones(Stage stage) {
        // llamar elemento del DOM para obtener opciones de turnos a partir de los datos en Opcion opcion
        List<com.programacion.cuatro.Classes.Turno> opciones;

        try {opciones = turnoBusinessObject.obtenerTurnos(opcion);}
        catch (Exception ex) { opciones = new ArrayList<com.programacion.cuatro.Classes.Turno>(); }

        TableView<com.programacion.cuatro.Classes.Turno> tableOpciones = new TableView<com.programacion.cuatro.Classes.Turno>();
        tableOpciones.setItems(FXCollections.observableArrayList(opciones));

        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory("fecha"));
        TableColumn horaCol = new TableColumn("Hora");
        horaCol.setCellValueFactory(new PropertyValueFactory("hora"));
        TableColumn mecanicoCol = new TableColumn("Mecanico");
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanicoNombre"));
        tableOpciones.getColumns().addAll(fechaCol, horaCol, mecanicoCol);

        addButtonToTable(tableOpciones);

        VBox vbox = new VBox(tableOpciones);
        vbox.getChildren().add(getBackButton());
        Scene scene = new Scene(vbox, 800, 600);

        stage.setScene(scene);
        stage.show();
    }

    private  Button getBackButton() {
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

    /// Confirma un turno de la lista previa.
    private void reservarTurno(com.programacion.cuatro.Classes.Turno turno, ActionEvent event) throws IOException, InterruptedException {


        int turnoN = turnoBusinessObject.addTurno(turno, opcion);

        if(turnoN > 0){
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            applicationContext.publishEvent(new ProgramacionFourApplication.StageReadyEvent(stage,BuscadorController.class));

        }
    }

    @FXML private TextField BuscadorTurnoTextField;
    @FXML private TextField BuscadorPatenteTextField;

    @FXML private TextField TurnoTextField;
    @FXML private TextField datosTurnoText;
    @FXML private TextField datosTurnoSecondText;
    //region Turnos XML Generation
    @FXML private ComboBox<String> especialidadCombo;
    @FXML private Label selectedEspecialidad;

    @FXML private ComboBox<String> companiaCombo;
    @FXML private Label selectedCompania;

    @FXML private TextField PatenteTextField;
    @FXML private TextField ClienteTextField;

    @FXML private DatePicker FechaSeleccionada;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        especialidadCombo.getItems().setAll(turnoBusinessObject.obtenerEspecialidades());
        selectedEspecialidad.textProperty().bind(especialidadCombo.getSelectionModel().selectedItemProperty());
        try {
            companiaCombo.getItems().setAll(turnoBusinessObject.getCompanias());
        }
        catch (Exception ex){}

        selectedCompania.textProperty().bind(companiaCombo.getSelectionModel().selectedItemProperty());

       /* if (this.opcion == null)
            opcion = new Opcion();*/


    }

    @FXML
    protected void datosTurnoButtonClick(ActionEvent event) {
        numeroTurno = this.TurnoTextField.getText();

        TurnoEntity turno = turnoBusinessObject.obtenerTurno(numeroTurno);

        if(turno == null) {
            datosTurnoText.setText("Turno "+numeroTurno+" no encontrado.");
        }
        else {
            MecanicoEntity mecanico = turnoBusinessObject.obtenerMecanico(turno.getMecanico().getId());
            String formatoCalendario = turno.getFecha().toString();
            String FormatoHora = turno.getHora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+ mecanico.getNombre()+", "+mecanico.getEspecialidad());
        }
    }

    private void addButtonToTable(TableView table) {
        TableColumn<com.programacion.cuatro.Classes.Turno, Void> colBtn = new TableColumn("Reservar");
        // refactor from https://riptutorial.com/javafx/example/27946/add-button-to-tableview
        Callback<TableColumn<com.programacion.cuatro.Classes.Turno, Void>, TableCell<com.programacion.cuatro.Classes.Turno, Void>> cellFactory
                = new Callback<TableColumn<com.programacion.cuatro.Classes.Turno, Void>, TableCell<com.programacion.cuatro.Classes.Turno, Void>>() {
            @Override
            public TableCell<com.programacion.cuatro.Classes.Turno, Void> call(final TableColumn<com.programacion.cuatro.Classes.Turno, Void> param) {
                final TableCell<com.programacion.cuatro.Classes.Turno, Void> cell = new TableCell<com.programacion.cuatro.Classes.Turno, Void>() {

                    private final Button btn = new Button("Reservar");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            com.programacion.cuatro.Classes.Turno turno = getTableView().getItems().get(getIndex());
                            try {
                                reservarTurno(turno, event);
                            } catch (IOException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);
    }
    //endregion
}
