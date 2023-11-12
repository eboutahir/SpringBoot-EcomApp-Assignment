package com.example.EcomApp.Config;

import com.example.EcomApp.dto.ErrarDto;
import com.example.EcomApp.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class RestExceptionHandler {

    @ExceptionHandler(value = { AppException.class })
    @ResponseBody
    public ResponseEntity<ErrarDto> handleException(AppException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ErrarDto(ex.getMessage()));
    }

}
