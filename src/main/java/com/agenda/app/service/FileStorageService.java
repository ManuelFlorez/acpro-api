package com.agenda.app.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Log4j2
public class FileStorageService implements IFileStorageService {

    private Path dirReport;

    @Autowired
    public FileStorageService() {
        this.dirReport = Paths.get("reportes");
        try {
            Files.createDirectories(this.dirReport);
        } catch (IOException e) {
            log.error("Error en Store", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Resource loadReport(String fileName) {
        Path filePath = dirReport.resolve(fileName);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                log.error("File not found " + fileName);
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException e) {
            log.error("File not found " + fileName);
            throw new RuntimeException("File not found " + fileName);
        }
    }
}
