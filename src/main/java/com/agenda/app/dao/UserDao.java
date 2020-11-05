package com.agenda.app.dao;

import com.agenda.app.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByCorreo(String correo);
}
