package com.programacion.cuatro.Controllers;

import com.programacion.cuatro.Business.TurnoBusinessObject;
import com.programacion.cuatro.Entities.FichaMecanicaEntity;
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

@FxmlView("/Views/conformidad-view.fxml")
@Controller
public class ConformidadController extends BaseController  {
    private String numeroTurno;

    @Autowired
    TurnoBusinessObject turnoBusinessObject;

    @FXML
    protected void datosTurnoButtonClick(ActionEvent event) {
        numeroTurno = this.numeroTurnoText.getText();
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

            actividadesText.setText(fichaMecanica.getActividades());
            insumosText.setText(fichaMecanica.getRepuestos());
        }
    }

    @FXML protected void conformeButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.numeroTurnoText.getText();
        turnoBusinessObject.firmaConforme(numeroTurno);
        backToHome(event);
    }

    @FXML protected void inconformeButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.numeroTurnoText.getText();
        turnoBusinessObject.firmaInconforme(numeroTurno);
        backToHome(event);
    }

    // ASISTENCIA
    @FXML private TextField numeroTurnoText;

    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @FXML private Label actividadesText;
    @FXML private Label insumosText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
