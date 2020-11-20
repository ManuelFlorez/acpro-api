package com.agenda.app.controllers;

import com.agenda.app.dao.PaqueteDao;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.entity.Paquete;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/paquete")
@CrossOrigin("*")
public class PaqueteController {

    @Autowired
    private PaqueteDao paqueteDao;

    @GetMapping("/all")
    public ResponseDto<List<Paquete>> index() {
        return ResponseDto.ok(paqueteDao.findAll());
    }

    @PostMapping("/create/{paquete}")
    public ResponseDto<List<String>> create(@PathVariable(value = "paquete") String paquete) {
        if (paquete == null || paquete.isEmpty()) {
            return ResponseDto.ok("debe ingresar el nombre del paquete", false);
        }
        Paquete paqueteEnity = new Paquete();
        paqueteEnity.setNombre(paquete);
        paqueteDao.save(paqueteEnity);
        return ResponseDto.ok(paqueteDao.findAll());
    }

}
