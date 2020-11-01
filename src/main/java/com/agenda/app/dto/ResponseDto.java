package com.agenda.app.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private T data;
    private boolean status = true;

    public ResponseDto(T data) {
        this.data = data;
    }

    public ResponseDto(T data, boolean status) {
        this.data = data;
        this.status = status;
    }

    public static ResponseDto ok(Object data) {
        return new ResponseDto(data);
    }

    public static ResponseDto ok(Object data, boolean status) {
        return new ResponseDto(data, status);
    }
}
