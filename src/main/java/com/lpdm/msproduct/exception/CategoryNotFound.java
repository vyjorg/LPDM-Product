package com.lpdm.msproduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotFound extends RuntimeException{
    public CategoryNotFound(String s) {
        super(s);
    }

}
