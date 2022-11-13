package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.Business.EstadoTurnoEnum;
import com.programacion.cuatro.Business.TurnoBusinessObject;
import com.programacion.cuatro.Entities.MecanicoEntity;
import com.programacion.cuatro.Entities.TurnoEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FxmlView("/Views/asistencia-view.fxml")
@Controller
public class AsistenciaController extends BaseController  {
    private String numeroTurno;

    @Autowired
    TurnoBusinessObject turnoBusinessObject;

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
            datosTurnoSecondText.setText("Mecánico "+mecanico.getNombre()+", "+mecanico.getEspecialidad());
        }
    }


    @FXML
    protected void confirmarTurnoButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.TurnoTextField.getText();
        com.programacion.cuatro.Classes.Turno turno = turnoBusinessObject.obtenerTurnoCompleto(numeroTurno);

        if(turno == null) {
            datosTurnoText.setText("No se encontró un el turno ");
        }
        else if(turno != null && turno.getEstadoTurno() != EstadoTurnoEnum.CREADO)
            datosTurnoText.setText("No se puede confirmar un turno con estado " + turno.getEstadoTurno().toString());
        else {
            try{
                turnoBusinessObject.registrarAsistencia(numeroTurno);
                backToHome(event);
            }
            catch(Exception ex){
                datosTurnoText.setText("No se encontró un el turno "+numeroTurno);
            }
        }
    }

    @FXML
    protected void cancelarTurnoButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.TurnoTextField.getText();
        com.programacion.cuatro.Classes.Turno turno = turnoBusinessObject.obtenerTurnoCompleto(numeroTurno);

        if(turno != null && turno.getEstadoTurno() != EstadoTurnoEnum.CREADO)
            datosTurnoText.setText("No se puede cancelar un turno con estado " + turno.getEstadoTurno().toString());
        else {
            try{
                turnoBusinessObject.cancelarTurno(numeroTurno);
                backToHome(event);
            }
            catch(Exception ex){
                datosTurnoText.setText("No se encontró un el turno "+numeroTurno+" para cancelar");
            }
        }
    }

    // ASISTENCIA
    @FXML private TextField TurnoTextField;

    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @FXML private Button confirmarTurnoButton;
    @FXML private Button cancelarTurnoButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
