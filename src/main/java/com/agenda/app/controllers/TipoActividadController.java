package com.agenda.app.controllers;

import com.agenda.app.dao.TipoActividadDao;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.entity.TipoActividad;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tipoActividad")
@CrossOrigin("*")
public class TipoActividadController {

    @Autowired
    private TipoActividadDao tipoActividadDao;

    @GetMapping("/all")
    public ResponseDto<List<String>> findAll() {
        return ResponseDto.ok(tipoActividadDao.findAll());
    }

    @PostMapping("/create/{actividad}")
    public ResponseDto<List<String>> create(@PathVariable(value = "actividad") String actividad) {
        if (actividad == null || actividad.isEmpty()) {
            return ResponseDto.ok("debe ingresar el nombre de la actividad", false);
        }
        TipoActividad tipoActividad = new TipoActividad();
        tipoActividad.setNombre(actividad.toUpperCase());
        tipoActividadDao.save(tipoActividad);
        return ResponseDto.ok(tipoActividadDao.findAll());
    }

}
