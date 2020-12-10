package com.agenda.app.dao;

import com.agenda.app.entity.Actividad;
import com.agenda.app.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActividadDao extends CrudRepository<Actividad, Integer> {

    List<Actividad> findByUsuario(User user);

    @Query(nativeQuery = true,
    value = "SELECT count(*) FROM actividad where acti_fecha > ?1 and acti_fecha < ?2 ")
    int contarPorMes(String fechaInicio, String fechaFin);

}
