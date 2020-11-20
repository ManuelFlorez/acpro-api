package com.agenda.app.dao;

import com.agenda.app.entity.Paquete;
import org.springframework.data.repository.CrudRepository;

public interface PaqueteDao extends CrudRepository<Paquete, Integer> {
}
