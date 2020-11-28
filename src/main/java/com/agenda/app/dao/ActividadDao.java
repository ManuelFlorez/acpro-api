package com.agenda.app.dao;

import com.agenda.app.entity.Actividad;
import com.agenda.app.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActividadDao extends CrudRepository<Actividad, Integer> {

    List<Actividad> findByUsuario(User user);

}
