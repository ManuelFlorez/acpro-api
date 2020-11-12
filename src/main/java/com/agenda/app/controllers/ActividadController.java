package com.agenda.app.controllers;

import com.agenda.app.dao.ActividadDao;
import com.agenda.app.dao.TipoActividadDao;
import com.agenda.app.dao.TipoResponsableDao;
import com.agenda.app.dao.UserDao;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.dto.payload.ActividadPayload;
import com.agenda.app.entity.Actividad;
import com.agenda.app.entity.TipoActividad;
import com.agenda.app.entity.TipoResponsable;
import com.agenda.app.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/actividad")
@CrossOrigin("*")
public class ActividadController {

    @Autowired
    private ActividadDao actividadDao;

    @Autowired
    private TipoActividadDao tipoActividadDao;

    @Autowired
    private TipoResponsableDao tipoResponsableDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/all")
    public ResponseDto<List<Actividad>> findAll() {
        return ResponseDto.ok(actividadDao.findAll()) ;
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
        if (tipoActividad == null || tipoResponsable == null || user == null) {
            return ResponseDto.ok("Datos incorrectos: tipoResponsableId o tipoActividadId", false);
        }
        Actividad actividad = new Actividad();
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
        actividadDao.save(actividad);
        return ResponseDto.ok("Registro Actividad con éxito.");
    }

}
