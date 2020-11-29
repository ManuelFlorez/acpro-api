package com.agenda.app.controllers;

import com.agenda.app.dto.ResponseDto;
import com.agenda.app.service.IFileStorageService;
import com.agenda.app.service.ReporteExcel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("/reporte")
@CrossOrigin("*")
public class ReporteController {

    @Autowired
    private ReporteExcel reporteExcel;
    @Autowired
    private IFileStorageService fileStorageService;

    @GetMapping("/uno")
    public ResponseDto crearInforme() {
        reporteExcel.reporteUno();
        return ResponseDto.ok("Exito");
    }

    @GetMapping("/downloaReport")
    public ResponseEntity<Resource> downloadAssignment() {
        reporteExcel.reporteUno();
        Resource resource = fileStorageService.loadReport("reporte.xls");

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-disposition", String.format("attachment;filename=%s", resource.getFilename()));

            headers.setContentLength(resource.contentLength());
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (IOException ex) {
            log.error("Content-Type", ex);
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
