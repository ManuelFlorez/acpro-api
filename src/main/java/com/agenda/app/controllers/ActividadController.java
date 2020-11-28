package com.agenda.app.controllers;

import com.agenda.app.dao.*;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.dto.payload.ActividadPayload;
import com.agenda.app.entity.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Log4j2
@RestController
@RequestMapping("/actividad")
@CrossOrigin("*")
public class ActividadController {

    @Autowired
    private PaqueteDao paqueteDao;

    @Autowired
    private ActividadDao actividadDao;

    @Autowired
    private TipoActividadDao tipoActividadDao;

    @Autowired
    private TipoResponsableDao tipoResponsableDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/all/{userId}")
    public ResponseDto<List<Actividad>> findAll(@PathVariable(value = "userId") Integer userId) {
        User user = userDao.findById(userId).orElse(null);
        if (user != null) {
            if (user.getRol().equals("administrador")) {
                List lista = limpiar((List<Actividad>) actividadDao.findAll());
                return ResponseDto.ok(lista);
            }
            List lista = limpiar((List<Actividad>) actividadDao.findByUsuario(user));
            return ResponseDto.ok(lista);
        }
        return ResponseDto.ok("No se identifico el usuario", false) ;
    }

    private List limpiar(List<Actividad> lista) {
        for (Actividad actividad : lista) {
            actividad.setUsuario(null);
        }
        return lista;
    }

    @PostMapping("/create")
    public ResponseDto<String> create(@Valid @RequestBody ActividadPayload actividadPayload) {
        return save(actividadPayload);
    }

    @PostMapping("/edit")
    public ResponseDto<String> edit(@Valid @RequestBody ActividadPayload actividadPayload) {
        Actividad actividad = actividadDao.findById(actividadPayload.getId()).orElse(null);
        if (actividad == null) {
            return ResponseDto.ok("No se encontro la actividad en el sistema", false);
        }
        return this.save(actividadPayload);
    }

    private ResponseDto<String> save(ActividadPayload actividadPayload) {
        User user = userDao.findById(actividadPayload.getUsuarioId()).orElse(null);
        TipoActividad tipoActividad = tipoActividadDao.findById(actividadPayload.getTipoActividadId())
                .orElse(null);
        TipoResponsable tipoResponsable = tipoResponsableDao.findById(actividadPayload.getTipoResponsableId())
                .orElse(null);
        Paquete paquete = paqueteDao.findById(actividadPayload.getPaqueteId()).orElse(null);
        if (tipoActividad == null || tipoResponsable == null || user == null || paquete == null) {
            log.info(tipoActividad);
            log.info(tipoResponsable);
            log.info(user);
            log.info(paquete);
            return ResponseDto.ok("Datos incorrectos: tipoResponsableId o tipoActividadId", false);
        }
        Actividad actividad = new Actividad();
        actividad.setPaquete(paquete);
        actividad.setUsuario(user);
        actividad.setNombre(actividadPayload.getNombre());
        actividad.setTipoActividad(tipoActividad);
        actividad.setTipoResponsable(tipoResponsable);
        actividad.setNombreResponsable(actividadPayload.getNombreResponsable());
        actividad.setDescripcion(actividadPayload.getDescripcion());
        actividad.setCiudad(actividadPayload.getCiudad());
        actividad.setPais(actividadPayload.getPais());
        actividad.setNumeroEstudiantes(actividadPayload.getNumeroEstudiantes());
        actividad.setNumeroDocentes(actividadPayload.getNumeroDocentes());
        actividad.setNumeroPersonas(actividadPayload.getNumeroPersonas());
        actividad.setNumeroPersonasAdministrativo(actividadPayload.getNumeroPersonasAdministrativo());
        actividad.setFecha(fechaRecibe(actividadPayload.getFecha()));
        actividad.setFechaRegistro(new Date());
        actividadDao.save(actividad);
        return ResponseDto.ok("Registro Actividad con éxito.");
    }

    private Date fechaRecibe(String fecha) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaA = null;
        try {
            fechaA = simpleDateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaA;
    }
}
