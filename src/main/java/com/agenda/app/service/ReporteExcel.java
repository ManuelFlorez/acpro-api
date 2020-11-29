package com.agenda.app.service;

import com.agenda.app.dao.ActividadDao;
import com.agenda.app.entity.Actividad;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class ReporteExcel {

    @Autowired
    private ActividadDao actividadDao;

    private void encabezado(HSSFSheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Fecha");
        header.createCell(1).setCellValue("Nombre Actividad");
        header.createCell(2).setCellValue("Paquete de Actividades");
        header.createCell(3).setCellValue("Tipo de Actividad");
        header.createCell(4).setCellValue("Tipo de Responsable");
        header.createCell(5).setCellValue("Responsable");
        header.createCell(6).setCellValue("Ciudad");
        header.createCell(7).setCellValue("Pais");
        header.createCell(8).setCellValue("Descripción");
        header.createCell(9).setCellValue("Numero de Estudiantes");
        header.createCell(10).setCellValue("Numero de Docentes");
        header.createCell(11).setCellValue("Numero de Personas");
        header.createCell(12).setCellValue("Numero de Administrativos");
    }

    public void reporteUno() {
        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet("reporte");

        encabezado(hoja);

        List<Actividad> actividades = (List<Actividad>) actividadDao.findAll();

        if (actividades == null) {
            return;
        }

        String name = "reportes/reporte.xls";
        try {
            FileOutputStream elFichero = new FileOutputStream(name);
            int x = 1;
            for(Actividad actividad : actividades) {
                HSSFRow fila = hoja.createRow(x++);
                createActividadFila(actividad, fila);
            }
            libro.write(elFichero);
            elFichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createActividadFila(Actividad actividad, HSSFRow fila) {
        HSSFCell celda = fila.createCell((short)0);
        HSSFRichTextString texto = new HSSFRichTextString(fechaFormat(actividad.getFecha()));
        celda.setCellValue(texto);

        celda = fila.createCell((short)1);
        texto = new HSSFRichTextString(actividad.getNombre());
        celda.setCellValue(texto);

        celda = fila.createCell((short)2);
        texto = new HSSFRichTextString(actividad.getPaquete().getNombre());
        celda.setCellValue(texto);

        celda = fila.createCell((short)3);
        texto = new HSSFRichTextString(actividad.getTipoActividad().getNombre());
        celda.setCellValue(texto);

        celda = fila.createCell((short)4);
        texto = new HSSFRichTextString(actividad.getTipoResponsable().getNombre());
        celda.setCellValue(texto);

        celda = fila.createCell((short)5);
        texto = new HSSFRichTextString(actividad.getNombreResponsable());
        celda.setCellValue(texto);

        celda = fila.createCell((short)6);
        texto = new HSSFRichTextString(actividad.getCiudad());
        celda.setCellValue(texto);

        celda = fila.createCell((short)7);
        texto = new HSSFRichTextString(actividad.getPais());
        celda.setCellValue(texto);

        celda = fila.createCell((short)8);
        texto = new HSSFRichTextString(actividad.getDescripcion());
        celda.setCellValue(texto);

        celda = fila.createCell((short)9);
        Integer numero = actividad.getNumeroEstudiantes();
        celda.setCellValue(numero);

        celda = fila.createCell((short)10);
        numero = actividad.getNumeroDocentes();
        celda.setCellValue(numero);

        celda = fila.createCell((short)11);
        numero = actividad.getNumeroPersonas();
        celda.setCellValue(numero);

        celda = fila.createCell((short)12);
        numero = actividad.getNumeroPersonasAdministrativo();
        celda.setCellValue(numero);
    }

    private String fechaFormat(Date fecha) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(fecha);
        return date;
    }

}
