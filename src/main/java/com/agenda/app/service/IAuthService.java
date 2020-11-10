package com.agenda.app.service;

import com.agenda.app.dao.UserDao;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.dto.UserDto;
import com.agenda.app.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class IAuthService implements IAuth {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseDto authentication(UserDto userDto) {
        User res = consultarBaseDatos(userDto);
        if (res != null) {
            return ResponseDto.ok(res);
        }
        return ResponseDto.ok(null, false);
    }

    private User consultarBaseDatos(UserDto user) {
        if (user.equals(userAdmin())) {
            User userAdmin = new User();
            userAdmin.setRol("administrador");
            userAdmin.setCorreo("manueljoaquinfa@ufps.edu.co");
            return userAdmin;
        }
        User userB = userDao.findByCorreo(user.getEmail());
        if (userB == null) {
            return null;
        }
        if (user.getEmail().equals(userB.getCorreo()) && user.getPassword().equals(userB.getClave())) {
            userB.setClave(null);
            return userB;
        }
        return null;
    }

    private UserDto userAdmin() {
        return UserDto.builder()
                .email("manueljoaquinfa@ufps.edu.co")
                .password("654123")
                .build();
    }

}
