package com.agenda.app.controllers;

import com.agenda.app.dto.ResponseDto;
import com.agenda.app.entity.Actividad;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/actividad")
@CrossOrigin("*")
public class ActividadController {

    @GetMapping("/all")
    public ResponseDto<List<Actividad>> findAll() {
        return null;
    }

    @PostMapping("/create")
    public ResponseDto<List<Actividad>> create() {
        return null;
    }

}
