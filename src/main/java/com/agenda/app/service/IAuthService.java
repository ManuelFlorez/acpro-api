package com.agenda.app.service;

import com.agenda.app.dto.ResponseDto;
import com.agenda.app.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class IAuthService implements IAuth {

    @Override
    public ResponseDto authentication(UserDto userDto) {
        if (consultarBaseDatos(userDto)) {
            return ResponseDto.ok("token 1");
        }
        return ResponseDto.ok("error: credenciales incorrectas", false);
    }

    private boolean consultarBaseDatos(UserDto user) {
        if (user.equals(userAdmin())) {
            return true;
        }
        UserDto userBd = UserDto.builder()
                .code("1151039")
                .email("manuelflorezw@gmail.com")
                .password("123456")
                .build();
        return user.equals(userBd);
    }

    private UserDto userAdmin() {
        return UserDto.builder()
                .code("1151039")
                .email("manueljoaquinfa@ufps.edu.co")
                .password("654123")
                .build();
    }

}
