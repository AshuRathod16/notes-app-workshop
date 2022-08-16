package com.bridgelabz.notesapp.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseUtil {
    private int errorCode;
    private String message;
    private Object token;

    public ResponseUtil() {
        this.errorCode = errorCode;
        this.message = message;
    }

}
