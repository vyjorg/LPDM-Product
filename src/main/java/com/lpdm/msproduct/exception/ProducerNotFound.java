package com.lpdm.msproduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProducerNotFound extends RuntimeException{
    public ProducerNotFound(String s) {
        super(s);
    }
}
