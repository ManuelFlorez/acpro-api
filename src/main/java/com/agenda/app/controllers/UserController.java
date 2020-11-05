package com.agenda.app.controllers;

import com.agenda.app.dao.UserDao;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.dto.payload.UsuarioRegistro;
import com.agenda.app.entity.User;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/all")
    public ResponseDto<List<User>> index() {
        return ResponseDto.ok(userDao.findAll());
    }

    @PostMapping("/registro")
    public ResponseDto<String> registar(@RequestBody UsuarioRegistro usuarioRegistro) {
        User user = userDao.findByCorreo(usuarioRegistro.getCorreo());
        if (user != null) {
            return ResponseDto.ok("Ya esta registrado este email: " + usuarioRegistro.getCorreo(), false);
        }
        user = new User();
        user.setCorreo(usuarioRegistro.getCorreo());
        user.setClave(usuarioRegistro.getClave());
        userDao.save(user);
        return ResponseDto.ok("se ha creado el nuevo usuario");
    }

    @GetMapping("/email/{email}")
    public ResponseDto<User> findEmail(@PathVariable(value = "email") String correo) {
        User user = userDao.findByCorreo(correo);
        if (user != null) {
            user.setClave(null);
        }
        return ResponseDto.ok(user);
    }
}
