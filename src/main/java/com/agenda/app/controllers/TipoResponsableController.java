package com.agenda.app.controllers;

import com.agenda.app.dao.TipoResponsableDao;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.entity.TipoResponsable;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tipoResponsable")
@CrossOrigin("*")
public class TipoResponsableController {
    @Autowired
    private TipoResponsableDao tipoResponsableDao;

    @GetMapping("/all")
    public ResponseDto<List<String>> findAll() {
        return ResponseDto.ok(tipoResponsableDao.findAll());
    }

    @PostMapping("/create/{responsable}")
    public ResponseDto<List<String>> create(@PathVariable(value = "responsable") String responsable) {
        if (responsable == null || responsable.isEmpty()) {
            return ResponseDto.ok("debe ingresar el nombre de responsable", false);
        }
        TipoResponsable tipoResponsable = new TipoResponsable();
        tipoResponsable.setNombre(responsable);
        tipoResponsableDao.save(tipoResponsable);
        return ResponseDto.ok(tipoResponsableDao.findAll());
    }

}
