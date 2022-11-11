package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Classes.Opcion;
import com.programacion.cuatro.Classes.Turno;
import com.programacion.cuatro.Entities.*;
import com.programacion.cuatro.Repositories.TurnoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class TurnoDAO implements IDAOTurno {

   @Autowired
    FichaMecanicaDAO _fichaMecanicaDAO;
   @Autowired
   FichaConformidadDAO _fichaConformidadDAO;

   @Autowired
    HorarioAtencionDAO _horarioAtencionDAO;
   @Autowired
   VehiculoDAO _vehiculosDao = new VehiculoDAO();
   @Autowired
   MecanicoDAO _mecanicosDAO = new MecanicoDAO();

    Opcion _opcion;


    @Autowired
    TurnoEntityRepository turnoEntityRepository;

    public int CreateTurno(TurnoEntity p) throws Exception {
        return turnoEntityRepository.saveAndFlush(p).getId();
    }



    public TurnoEntity ReadTurno(Integer id) throws Exception {

        Optional<TurnoEntity> turnoEntityOptional = turnoEntityRepository.findById(id);

        if(turnoEntityOptional.isPresent())
            return turnoEntityOptional.get();
        else
            return null;
    }


    public void UpdateTurno(TurnoEntity p) throws Exception {

       turnoEntityRepository.saveAndFlush(p);
    }


    public List<TurnoEntity> obtenerTurnos(String patente){
        try {
            return GetTurnoByPatente(patente);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<TurnoEntity> GetTurnoByPatente(String patente) {
        return turnoEntityRepository.findByVehiculo_Patente(patente);
    }

    public List<TurnoEntity> obtenerTurnos(LocalDate fechaDesde, LocalDate fechaHasta, boolean onlyActive) {
        try {
            return GetTurnoByFechas(fechaDesde,fechaHasta,onlyActive);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<TurnoEntity> GetTurnoByFechas(LocalDate fechaDesde, LocalDate fechaHasta, boolean onlyActive) {

            if(onlyActive){
              return turnoEntityRepository.findByFechaBetweenAndActive(fechaDesde,fechaHasta, true);
            }else{
               return  turnoEntityRepository.findByFechaBetween(fechaDesde,fechaHasta);
            }

    }

    public List<Turno> obtenerTurnosC(Opcion opcion, List<MecanicoEntity> mecanicos) throws Exception {
        _opcion = opcion;
        List<Turno> opciones = new ArrayList<Turno>();
        List<TurnoEntity> turnosDados = GetTurnoByFechas(opcion.getFecha(), opcion.getFecha().plusDays(7), true);

        for (int i = 0; i < 8; i++) {
            if(opcion.getFecha().plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY)
                continue;

            for (MecanicoEntity mecanico : mecanicos) {
                List<HorarioAtencionEntity> horariosAtencion = _horarioAtencionDAO.getByMecanico(mecanico.getId());
                for (HorarioAtencionEntity horario : horariosAtencion){
                    // Buscar quien trabaja el día esperado.
                    LocalDate fecha = opcion.getFecha().plusDays(i);
                    if(! fecha.getDayOfWeek().toString().equals(horario.getDiaAtencion()))
                        continue;
                    _opcion.setFecha(fecha);
                    _opcion.setMecanico(mecanico);

                    // armar slots de tiempo. 30m.
                    LocalTime horaDesde = horario.getHoraDesde();
                    LocalTime horaHasta = horario.getHoraHasta();
                    // ver si ya tiene trabajo previo.
                    while (horaDesde.isBefore(horaHasta)) {
                        _opcion.setHora(horaDesde);
                        if(turnosDados.stream().anyMatch(checkExists)) {
                            horaDesde = horaDesde.plusMinutes(30);
                            continue;
                        }

                        opciones.add(new com.programacion.cuatro.Classes.Turno(fecha, horaDesde, mecanico));
                        horaDesde = horaDesde.plusMinutes(30);
                    }
                }
            }
        }

        return opciones;
    }

    private Predicate<TurnoEntity> checkExists = new Predicate<TurnoEntity>() {
        @Override
        public boolean test(TurnoEntity turno) {
            return turno.getFecha().isEqual(_opcion.getFecha())
                    && turno.getHora().getHour() == _opcion.getHora().getHour()
                    && turno.getHora().getMinute() == _opcion.getHora().getMinute()
                    && turno.getMecanico().getId() == _opcion.getMecanico().getId();
        }
    };

    public TurnoEntity obtenerTurno(String id) {
        try {
            return ReadTurno(Integer.valueOf(id));
        }
        catch (Exception ex) {
            return null;
        }
    }

    public com.programacion.cuatro.Classes.Turno obtenerTurnoCompleto(String id) {
        try {
            com.programacion.cuatro.Classes.Turno turno = new com.programacion.cuatro.Classes.Turno();
            TurnoEntity turnoData = ReadTurno(Integer.valueOf(id));

            turno.setId(turnoData.getId());
            turno.setActive(turnoData.getActive());
            turno.setFecha(turnoData.getFecha());
            turno.setHora(turnoData.getHora());
            turno.setAsistnecia(turnoData.getAsistencia());

            /*
            FichaMecanicaEntity fichaMecanica = _fichaMecanicaDAO.obtenerFichaMecanica(turnoData.getFichaMecanica().getId());
            if(fichaMecanica != null) {
                FichaConformidadEntity fichaConformidad = _fichaConformidadDAO.obtenerFichaConformidad(fichaMecanica.getFichaConformidad().getId());
                turno.setFichaMecanica(new com.programacion.cuatro.Classes.FichaMecanica(
                        fichaMecanica.getId(), fichaMecanica.getActividades(), fichaMecanica.getRepuestos(), null));

                if(fichaConformidad != null)
                    turno.getFichaMecanica().setFichaConformidad(
                            new com.programacion.cuatro.Classes.FichaConformidad(fichaConformidad.getId(), fichaConformidad.getFirmadaConforme()
                            , fichaConformidad.getFirmada(), fichaConformidad.getMotivosDisconforme()));
            }*/

            return turno;
        }
        catch (Exception ex) {
            return null;
        }
    }

    public void registrarAsistencia(String id) {
        try {
            TurnoEntity turno = ReadTurno(Integer.valueOf(id));
            turno.setAsistencia(true);
            UpdateTurno(turno);
        }
        catch (Exception ex) {

        }
    }

    public void cancelarTurno(String id) {
        TurnoEntity turno = obtenerTurno(id);
        turno.setActive(false);
        turno.setAsistencia(false);
        try {UpdateTurno(turno);}
        catch (Exception ex) {}
    }



    public int addTurno(com.programacion.cuatro.Classes.Turno turno, Opcion opcion) {
        try {
            TurnoEntity turnoDTO = new TurnoEntity();
            turnoDTO.setFecha(turno.getFecha());
            turnoDTO.setHora(turno.getHora());
            turnoDTO.setActive(true);

            turnoDTO.setMecanico(_mecanicosDAO.obtenerMecanico(turno.getMecanico().getId()));
            turnoDTO.setVehiculo(_vehiculosDao.obtenerVehiculoPatente(opcion.getPatente(), opcion.getCompania(), opcion.getCliente()));
            turnoDTO.setAsistencia(false);

            FichaConformidadEntity fichaConformidad = new FichaConformidadEntity();
            fichaConformidad.setFirmada(false);
            fichaConformidad.setFirmadaConforme(false);
            fichaConformidad.setMotivosDisconforme("");
            _fichaConformidadDAO.CreateFichaConformidad(fichaConformidad);


            FichaMecanicaEntity fichaMecanica = new FichaMecanicaEntity();
            fichaMecanica.setActividades("");
            fichaMecanica.setRepuestos("");
            fichaMecanica.setFichaConformidad(fichaConformidad);
            _fichaMecanicaDAO.CreateFichaMecanica(fichaMecanica);


            turnoDTO.setFichaMecanica(fichaMecanica);

            return CreateTurno(turnoDTO);
        }
        catch (Exception ex) {
            return 0;}
    }

    public void registrarActividades(String numeroTurno, String actividadesText, String insumosText) {
        TurnoEntity turno = obtenerTurno(numeroTurno);
        try {
            _fichaMecanicaDAO.registrarActividades(turno.getFichaMecanica().getId(), actividadesText, insumosText);
        }
        catch (Exception ex) {}
    }

    public void firmaConforme(String numeroTurno) {
        TurnoEntity turno = obtenerTurno(numeroTurno);
        _fichaConformidadDAO.firmar(turno.getFichaMecanica().getFichaConformidad(), true);
    }

    public void firmaInconforme(String numeroTurno) {
        TurnoEntity turno = obtenerTurno(numeroTurno);
        _fichaConformidadDAO.firmar(turno.getFichaMecanica().getFichaConformidad(), false);
    }

    public String obtenerTurnoID() {
        try {
            Optional<TurnoEntity> turnoEntityOptional = turnoEntityRepository.findFirstByOrderByIdDesc();

            if(turnoEntityOptional.isPresent())
                return turnoEntityOptional.get().getId().toString();
            else
                return null;

        }
        catch (Exception ex) {return null;}
    }
}
