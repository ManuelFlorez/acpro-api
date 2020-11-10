package com.agenda.app.dao;

import com.agenda.app.entity.Actividad;
import org.springframework.data.repository.CrudRepository;

public interface ActividadDao extends CrudRepository<Actividad, Integer> {
}
