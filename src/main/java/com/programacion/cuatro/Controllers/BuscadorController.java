package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.Business.TurnoBusinessObject;
import com.programacion.cuatro.Classes.Opcion;
import com.programacion.cuatro.Entities.MecanicoEntity;
import com.programacion.cuatro.Entities.TurnoEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FxmlView("/Views/buscador-view.fxml")
@Controller
public class BuscadorController extends BaseController {
    private Opcion opcion;
    private String numeroTurno;

    @Autowired
    TurnoBusinessObject turnoBusinessObject;



    @FXML
    protected void onBuscarTurnoClick(ActionEvent event) throws IOException {
        TurnoEntity turno = null;
        if (BuscadorTurnoTextField != null && !BuscadorTurnoTextField.getText().isEmpty()) {
            turno = turnoBusinessObject.obtenerTurno(BuscadorTurnoTextField.getText());
        }
        else {
            if(BuscadorPatenteTextField != null && !BuscadorPatenteTextField.getText().isEmpty()) {
                try {
                    turno = turnoBusinessObject.obtenerTurnos(BuscadorPatenteTextField.getText())
                            .stream().findFirst().get();
                }
                catch (Exception ex) { }
            }
        }

        if(turno == null) {
            datosTurnoText.setText("Turno no encontrado.");
            datosTurnoSecondText.setText("");
        }
        else {
            MecanicoEntity mecanico = turnoBusinessObject.obtenerMecanico(turno.getMecanico().getId());

            String formatoCalendario = turno.getFecha().toString();
            String FormatoHora = turno.getHora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+ mecanico.getNombre()+", "+mecanico.getEspecialidad());
        }
    }

    @FXML private TextField BuscadorTurnoTextField;
    @FXML private TextField BuscadorPatenteTextField;

    @FXML private Label TurnoTextField;
    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BuscadorTurnoTextField.setText(turnoBusinessObject.obtenerTurnoID());
    }
}
