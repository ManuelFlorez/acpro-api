package com.agenda.app.controllers;

import com.agenda.app.dao.ActividadDao;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.entity.Actividad;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/actividad")
@CrossOrigin("*")
public class ActividadController {

    @Autowired
    private ActividadDao actividadDao;

    @GetMapping("/all")
    public ResponseDto<List<Actividad>> findAll() {
        return ResponseDto.ok(actividadDao.findAll()) ;
    }

    @PostMapping("/create")
    public ResponseDto<List<Actividad>> create() {
        return null;
    }

}
