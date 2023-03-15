package com.br.actionsitesale.controller;

import com.br.actionsitesale.exception.BusinessException;
import com.br.actionsitesale.exception.ErrorResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Getter
@Setter
@RestControllerAdvice
public class ControllerExceptionHandler {
    private String code;
    private String description;

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ErrorResponse> handing(BusinessException exception ) {
        return new ResponseEntity<>(errorDetail(exception), status(code) );
    }

    private ErrorResponse errorDetail(BusinessException exception) {
        String message[] = exception.getMessage().split("#");
        description = message[0];
        code = message[1];
        String dateFormated = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return new ErrorResponse(description, dateFormated);
    }
    private HttpStatus status(String code) {
        if (code.equals("FORBIDDEN"))
            return HttpStatus.FORBIDDEN;

        else if(code.equals("NOT_FOUND"))
            return HttpStatus.NOT_FOUND;

        else
            return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
