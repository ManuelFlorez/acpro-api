package com.agenda.app.controllers;

import com.agenda.app.dto.ResponseDto;
import com.agenda.app.dto.UserDto;
import com.agenda.app.service.IAuth;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Log4j2
@Validated
@RestController
public class LoginController {
    // http://localhost:8080/api/swagger-ui/index.html
    @Autowired
    private IAuth auth;

    @PostMapping("/login")
    public ResponseDto singIn(@Valid @RequestBody UserDto userDto) {
        return auth.authentication(userDto);
    }

    @PostMapping("/logout")
    public ResponseDto singOut() {
        return ResponseDto.ok("Adíos!");
    }

}
