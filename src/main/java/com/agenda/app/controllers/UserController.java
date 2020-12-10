package com.agenda.app.controllers;

import com.agenda.app.dao.UserDao;
import com.agenda.app.dto.ResponseDto;
import com.agenda.app.dto.payload.UsuarioEditPerfil;
import com.agenda.app.dto.payload.UsuarioRecoverPassword;
import com.agenda.app.dto.payload.UsuarioRegistro;
import com.agenda.app.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Log4j2
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/all")
    public ResponseDto<List<User>> index() {
        List<User> users = (List<User>) userDao.findAll();
        users.forEach(user -> user.setClave(null));
        return ResponseDto.ok(users);
    }

    @PostMapping("/registro")
    public ResponseDto<String> registar(@Valid @RequestBody UsuarioRegistro usuarioRegistro) {
        User user = userDao.findByCorreo(usuarioRegistro.getCorreo());
        if (user != null) {
            return ResponseDto.ok("Ya esta registrado este email: " + usuarioRegistro.getCorreo(), false);
        }
        user = new User();
        user.setCorreo(usuarioRegistro.getCorreo());
        user.setClave(usuarioRegistro.getClave());
        user.setCodigo("");
        user.setRol(usuarioRegistro.getTipoUsuario());
        user.setEstado("ACTIVO");
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
    
    @PostMapping("/recoverpassword")
    public ResponseDto<String> recoverpassword(@Valid @RequestBody UsuarioRecoverPassword usuarioRecoverPassword) {
        User user = userDao.findByCorreo(usuarioRecoverPassword.getCorreo());
        if (user == null) {
            return ResponseDto.ok("No esta registrado este email: " + usuarioRecoverPassword.getCorreo(), false);
        }
        user.setClave(usuarioRecoverPassword.getClave());
        userDao.save(user);
        return ResponseDto.ok("Se actualizo la clave");
    }

    @PostMapping("/editarPerfil")
    public ResponseDto<String> editarPerfil(@RequestBody UsuarioEditPerfil usuarioEditPerfil) {
        User user = userDao.findByCorreo(usuarioEditPerfil.getEmail());
        if (user == null) {
            return ResponseDto.ok("No se encontro el usuario", false);
        }
        user.setUsername(usuarioEditPerfil.getUsername());
        user.setCorreo(usuarioEditPerfil.getEmail());
        user.setNombres(usuarioEditPerfil.getNombres());
        user.setApellidos(usuarioEditPerfil.getApellidos());
        user.setDireccion(usuarioEditPerfil.getDireccion());
        user.setCiudad(usuarioEditPerfil.getCiudad());
        user.setPais(usuarioEditPerfil.getPais());
        user.setDescripcion(usuarioEditPerfil.getDescripcion());
        userDao.save(user);
        return ResponseDto.ok("Se actualizo la información de perfil");
    }
    
}
