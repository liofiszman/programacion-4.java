package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.Business.TurnoBusinessObject;
import com.programacion.cuatro.Entities.FichaMecanicaEntity;
import com.programacion.cuatro.Entities.MecanicoEntity;
import com.programacion.cuatro.Entities.TurnoEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@FxmlView("/Views/actividades-view.fxml")
@Controller
public class ActividadesController extends BaseController  {
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
            FichaMecanicaEntity fichaMecanica = turnoBusinessObject.obtenerFichaMecanica(turno.getFichaMecanica().getId());

            String formatoCalendario = turno.getFecha().toString();
            String FormatoHora = turno.getHora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+mecanico.getNombre()+", "+mecanico.getEspecialidad());

            actividadesTextField.setText(fichaMecanica.getActividades());
            insumosTextField.setText(fichaMecanica.getRepuestos());
        }
    }

    @FXML
    protected void registrarButtonClick(ActionEvent event) throws IOException {

        numeroTurno = this.TurnoTextField.getText();
        if(!numeroTurno.trim().isEmpty() && turnoBusinessObject.obtenerTurno(numeroTurno) != null) {

            String actividadesText = this.actividadesTextField.getText();
            String insumosText = this.insumosTextField.getText();
            turnoBusinessObject.registrarActividades(numeroTurno, actividadesText, insumosText);
            Dialog dialog = new Dialog();
            dialog.setTitle("");
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContentText("Actividades Registradas");
            dialogPane.getButtonTypes().addAll(ButtonType.OK);
            dialog.setDialogPane(dialogPane);
            backToHome(event);

        }else{

            Dialog dialog = new Dialog();
            dialog.setTitle("Error");
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContentText("Seleccione un turno valido");
            dialogPane.getButtonTypes().addAll(ButtonType.OK);
            dialog.setDialogPane(dialogPane);
            dialog.show();
        }
    }

    // ASISTENCIA
    @FXML private TextField TurnoTextField;

    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @FXML private TextField actividadesTextField;
    @FXML private TextField insumosTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
