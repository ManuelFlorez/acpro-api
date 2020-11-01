package com.agenda.app.service;

import com.agenda.app.dto.ResponseDto;
import com.agenda.app.dto.UserDto;

public interface IAuth {
    ResponseDto authentication(UserDto userDto);
}
