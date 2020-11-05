package com.agenda.app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ExceptionResponseDto {

    private String message;
    private LocalDateTime dateTime;
    private Map errors;
}
