package com.agenda.app.controllers;

import com.agenda.app.dao.ActividadDao;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@RestController
@RequestMapping("/reporte")
@CrossOrigin("*")
public class ReporteController {

    @Autowired
    private ReporteExcel reporteExcel;
    @Autowired
    private IFileStorageService fileStorageService;
    @Autowired
    private ActividadDao actividadDao;

    @GetMapping("/uno")
    public ResponseDto crearInforme() {
        reporteExcel.reporteUno();
        return ResponseDto.ok("Exito");
    }

    @GetMapping("/reportGrafi")
    public ResponseDto reportGrafic() {
        int[]meses = new int[12];
        for (int i = 0; i < 12; i++) {
            try {
                meses[i] = reporteMes(i);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return ResponseDto.ok(meses);
    }

    private int reporteMes(int mes) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String fechaStr = formateador.format(new Date());
        int year = Integer.parseInt(fechaStr.split("-")[0]);
        if (mes == 0) {
            --year;
        }
        if (mes == 11) {
            ++year;
        }
        String fechas[] = fechaAnexo(mes, year + "");
        return actividadDao.contarPorMes(fechas[0], fechas[1]);
    }

    private String[] fechaAnexo(int mes, String year) {
        String[]fechas = new String[2];
        switch (mes) {
            case 0: fechas[0] = year + "-12-31";   fechas[1] = year + "-01-31";
                break;
            case 1: fechas[0] = year + "-01-31";   fechas[1] = year + "-03-1";
                break;
            case 2: fechas[0] = year + "-02-30";   fechas[1] = year + "-04-1";
                break;
            case 3: fechas[0] = year + "-03-30";   fechas[1] = year + "-05-1";
                break;
            case 4: fechas[0] = year + "-04-30";   fechas[1] = year + "-06-1";
                break;
            case 5: fechas[0] = year + "-05-30";   fechas[1] = year + "-07-1";
                break;
            case 6: fechas[0] = year + "-06-30";   fechas[1] = year + "-08-1";
                break;
            case 7: fechas[0] = year + "-07-30";   fechas[1] = year + "-09-1";
                break;
            case 8: fechas[0] = year + "-08-30";   fechas[1] = year + "-10-1";
                break;
            case 9: fechas[0] = year + "-09-30";   fechas[1] = year + "-11-1";
                break;
            case 10: fechas[0] = year + "-10-30";   fechas[1] = year + "-12-1";
                break;
            case 11: String y =  ( Integer.parseInt(year) - 1 ) + "";
                fechas[0] = y + "-11-30";   fechas[1] = year + "-01-01";
                break;
        }
        return fechas;
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
