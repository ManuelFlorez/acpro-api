package com.agenda.app.service;

import org.springframework.core.io.Resource;

public interface IFileStorageService {
    public Resource loadReport(String fileName);
}
