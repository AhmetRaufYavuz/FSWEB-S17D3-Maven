package com.workintech.zoo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ZooException extends RuntimeException {

    private HttpStatus httpStatus;

    public ZooException(String string, HttpStatus httpStatus) {
        super(string);
        this.httpStatus = httpStatus;
    }
}
